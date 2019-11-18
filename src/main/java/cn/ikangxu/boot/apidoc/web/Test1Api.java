/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.web;

import cn.ikangxu.boot.apidoc.annotation.*;
import cn.ikangxu.boot.apidoc.common.HttpMethod;
import cn.ikangxu.boot.apidoc.common.ParamType;
import cn.ikangxu.boot.apidoc.common.test.TestEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author kangxu [xukang@engine3d.com]
 * @version v1.0
 * @className TestApi
 * @description
 * @date 2019/11/12 17:29
 */
@Api(name = "测试1", sort = 1)
@RestController
@RequestMapping("/api/test1")
public class Test1Api {

    @ApiMethod(
            name = "(√)测试字符串-test1",
            method = HttpMethod.GET,
            response = TestEntity.class,
            description = "这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等",
            discarded = false
    )
    @ApiParams({
            @ApiParam(name = "testParam1", description = "测试参数1", paramType = ParamType.QUERY, dataType = "string", required = true, allowMultiple = false, example = "测试参数示例1"),
            @ApiParam(name = "testParam2", description = "测试参数2", paramType = ParamType.QUERY, dataType = "string", required = true, allowMultiple = false, example = "测试参数示例2")
    })
    @ApiResponses({
            @ApiResponse(code = 200, msg = "成功", description = ""),
            @ApiResponse(code = 500, msg = "错误", description = "")
    })
    @GetMapping(value = "test3", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String test1(String testParam1, String testParam2) {
        return "{\"name\":\"" + testParam1 + "_" + testParam2 + "\"}";
    }

    @ApiMethod(
            name = "(√)测试输出图片-showImg",
            method = HttpMethod.GET,
            response = Void.class,
            description = "这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等",
            discarded = false
    )
    @ApiResponses({
            @ApiResponse(code = 200, msg = "成功", description = ""),
            @ApiResponse(code = 500, msg = "错误", description = "")
    })
    @GetMapping(value = "showImg")
    public void showImg(HttpServletResponse response) throws IOException {
        String path = "C:\\Users\\Administrator\\Desktop\\20150723115018_ma428.jpeg";
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);

        response.setContentType(getContentType(path));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[1024];
        int i = 0;
        while ((i = fileInputStream.read(bytes)) > 0) {
            outputStream.write(bytes, 0, i);
        }
    }

    @ApiMethod(
            name = "(√)测试输出视频-showVideo",
            method = HttpMethod.GET,
            response = Void.class,
            description = "这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等",
            discarded = false
    )
    @ApiResponses({
            @ApiResponse(code = 200, msg = "成功", description = ""),
            @ApiResponse(code = 500, msg = "错误", description = "")
    })
    @GetMapping(value = "showVideo")
    public void showVideo(HttpServletResponse response) throws IOException {
        String path = "C:\\Users\\Administrator\\Desktop\\f454d3add6834ed9a1149bd24e1c9d34.mp4";
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);

        response.setContentType(getContentType(path));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[1024];
        int i = 0;
        while ((i = fileInputStream.read(bytes)) > 0) {
            outputStream.write(bytes, 0, i);
        }
    }

    @ApiMethod(
            name = "(√)测试输出音频-showAudio",
            method = HttpMethod.GET,
            response = Void.class,
            description = "这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等",
            discarded = false
    )
    @ApiResponses({
            @ApiResponse(code = 200, msg = "成功", description = ""),
            @ApiResponse(code = 500, msg = "错误", description = "")
    })
    @GetMapping(value = "showAudio")
    public void showAudio(HttpServletResponse response) throws IOException {
        String path = "C:\\Users\\Administrator\\Documents\\Tencent Files\\1513087242\\FileRecv\\audio.mp3";
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);

        response.setContentType(getContentType(path));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[1024];
        int i = 0;
        while ((i = fileInputStream.read(bytes)) > 0) {
            outputStream.write(bytes, 0, i);
        }
    }

    @ApiMethod(
            name = "(√)测试文件上传-uploadFile",
            method = HttpMethod.POST,
            response = TestEntity.class,
            description = "这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等",
            discarded = false
    )
    @ApiParams({
            @ApiParam(name = "file", description = "文件", paramType = ParamType.FORM, dataType = "file", required = true, allowMultiple = false, example = "测试参数示例1")
    })
    @ApiResponses({
            @ApiResponse(code = 200, msg = "成功", description = ""),
            @ApiResponse(code = 500, msg = "错误", description = "")
    })
    @PostMapping(value = "uploadFile", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) {

        return "{\"name\":\"" + multipartFile.getOriginalFilename() + "\"}";
    }

    @ApiMethod(
            name = "(×)测试允许多值的参数-multipleSimpleParam",
            method = HttpMethod.GET,
            response = TestEntity.class,
            description = "这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等",
            discarded = false
    )
    @ApiParams({
            @ApiParam(name = "params", description = "测试参数1", paramType = ParamType.QUERY, dataType = "string", required = true, allowMultiple = true, example = "测试参数示例1")
    })
    @ApiResponses({
            @ApiResponse(code = 200, msg = "成功", description = ""),
            @ApiResponse(code = 500, msg = "错误", description = "")
    })
    @GetMapping(value = "multipleSimpleParam", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String multipleSimpleParam(String[] params) {
        return "{\"name\":\"" + params + "\"}";
    }

    @ApiMethod(
            name = "(√)测试多个Path-multiplePath",
            method = HttpMethod.GET,
            response = TestEntity.class,
            description = "这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等",
            discarded = false
    )
    @ApiResponses({
            @ApiResponse(code = 200, msg = "成功", description = ""),
            @ApiResponse(code = 500, msg = "错误", description = "")
    })
    @GetMapping(value = {"multiplePath", "multiplePath2"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String multiplePath() {
        return "测试多个Path";
    }

    @ApiMethod(
            name = "(√)测试多个Method-multipleMethod",
            method = HttpMethod.GET,
            response = TestEntity.class,
            description = "这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等",
            discarded = false
    )
    @ApiResponses({
            @ApiResponse(code = 200, msg = "成功", description = ""),
            @ApiResponse(code = 500, msg = "错误", description = "")
    })
    @RequestMapping(value = {"multipleMethod", "multipleMethod2"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String multipleMethod() {
        return "测试多个Method";
    }

    public static String getContentType(String filename) {
        String type = null;
        Path path = Paths.get(filename);
        try {
            type = Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return type;
    }

}
