# WhiteList

> 安卓平台白名单管理器，适配各个厂商的自启动管理、电源管理、后台管理等。
> 由于国内安卓机型种类繁多，还请大家贡献自己的测试机和代码，完善项目，方便大家。

## 贡献机型步骤

1. 运行项目，搜索Log `phoneInfo`，查看自己的机型
2. 打开相应功能页面，调用命令行 `adb shell dumpsys activity top` 查看页面信息
3. 新建 `xxxOperation` 类，继承 `DefaultOperation` 类，完善代码
4. `WhiteListManager` 类构造函数中注册自己的 `xxxOperation` 类
5. `MainActivity` 中写测试代码，运行调试
6. `README.md` 中完善信息

## 适配的机型
|厂商|手机型号|安卓版本|自启动|休眠|贡献人|
|---|---|---|---|---|---|
|三星（samsung）|SM-G9250|7.0|√|√|[niulinguo]|


[niulinguo]: https://github.com/niulinguo