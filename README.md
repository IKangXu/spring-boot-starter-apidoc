![Logo](./doc/logo.png)


### 调用示例

```java
    @ApiMethod(
            name = "测试接口3",
            method = HttpMethod.GET,
            response = Void.class,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            description = "",
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
        return "test3";
    }
```
