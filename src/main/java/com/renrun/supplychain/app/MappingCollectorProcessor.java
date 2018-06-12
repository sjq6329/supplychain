package com.renrun.supplychain.app;

import com.alibaba.fastjson.JSON;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 通过识别以下三个注解:
 * org.springframework.web.bind.annotation.RequestMapping
 * org.springframework.web.bind.annotation.RestController
 * com.wordnik.swagger.annotations.ApiOperation
 * 来扫描APP项目中的所有接口，生成`user_perm_perm`表的插入语句
 *
 * 需要在`pom.xml`文件的`<buid><plugins></plugins></buid>`节点内增加一个插件,如下:
 *
 * ```
 * <plugin>
 *     <groupId>org.bsc.maven</groupId>
 *     <artifactId>maven-processor-plugin</artifactId>
 *     <executions>
 *         <execution>
 *             <id>process</id>
 *             <goals>
 *                 <goal>process</goal>
 *             </goals>
 *             <phase>compile</phase>
 *             <configuration>
 *                 <processors>
 *                     <processor>com.renrun.supplychain.app.MappingCollectorProcessor</processor>
 *                 </processors>
 *             </configuration>
 *         </execution>
 *     </executions>
 * </plugin>
 * ```
 *
 * `<processor>`配的是注解处理器的路径
 *
 * 然后执行`mvn compile`
 * Created by swk on 2017/1/19.
 */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes({
        "org.springframework.web.bind.annotation.RequestMapping",
            "org.springframework.web.bind.annotation.RestController",
        "com.wordnik.swagger.annotations.ApiOperation"
})
public class MappingCollectorProcessor extends AbstractProcessor {
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("开始扫描接口");

        Set<? extends Element> elementSet = roundEnv.getRootElements();
        System.out.println(elementSet.size());

        List<ApiInfo> apiInfos = new ArrayList<>();

        for (Element e : elementSet) {
            System.out.println(e.getKind().toString());
            if(e.getKind().isClass()) {
                RestController ann = e.getAnnotation(RestController.class);
                if(ann != null) {
                    String parentPath = "";
                    RequestMapping rm = e.getAnnotation(RequestMapping.class);
                    if(rm != null) {
                        String[] paths = rm.value();
                        for(int i=0; i<paths.length; i++) {
                            System.out.println(paths[i]);
                            parentPath = paths[i];
                        }
                    }

                    Api api = e.getAnnotation(Api.class);
                    if(api != null) {
                        System.out.println(api.value());
                        System.out.println(api.description());
                    }
                    List<? extends Element> els = e.getEnclosedElements();

                    for(Element el : els) {
                        if(!el.getKind().equals(ElementKind.METHOD)) {
                            continue;
                        }

                        ApiInfo apiInfo = new ApiInfo();
                        apiInfo.parentPath = parentPath;

                        RequestMapping rmAn = el.getAnnotation(RequestMapping.class);
                        if(rmAn != null) {
                            RequestMethod[] methods = rmAn.method();
                            for(int i=0; i<methods.length; i++) {
                                System.out.println(methods[i]);
                                apiInfo.method = methods[i];
                            }
                            String[] paths = rmAn.value();
                            for(int i=0; i<paths.length; i++) {
                                System.out.println(paths[i]);
                                apiInfo.path = paths[i];
                            }
                        }

                        ApiOperation aoAn = el.getAnnotation(ApiOperation.class);
                        if(aoAn != null) {
                            System.out.println(aoAn.value());
                            System.out.println(aoAn.notes());
                            apiInfo.title = aoAn.value();
                        }

                        if(apiInfo.method != null) {
                            apiInfos.add(apiInfo);
                        }
                    }
                }
            }
        }

        System.out.println("接口扫描完毕，接口JSON数据：");

        System.out.println(JSON.toJSON(apiInfos));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO `user_perm_perm` (`method`, `path`, `name`) VALUES ");
        for(ApiInfo apiInfo : apiInfos) {
            String s = String.format(
                    "('%s','%s%s','%s'),",
                    apiInfo.method.toString(),
                    apiInfo.parentPath,
                    apiInfo.path,
                    apiInfo.title
            );
            stringBuilder.append(s);
        }

        System.out.println("接口SQL数据：");
        System.out.println(stringBuilder.toString());

        return false;
    }

    private static class ApiInfo {
        public RequestMethod method;
        public String parentPath;
        public String path;
        public String title;
    }
}
