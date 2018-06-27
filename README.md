# WhiteList

> 安卓平台白名单管理器，目标是可以适配和打开各个厂商的自启动管理页面、电源管理页面、后台管理页面等。用于引导用户将 App 添加到系统白名单
> 由于国内安卓机型种类繁多，还请大家贡献自己的测试机和代码，完善项目，方便大家。

## 贡献机型步骤

1. 运行项目，搜索Log `phoneInfo`，查看自己的机型
2. 打开相应功能页面，调用命令行 `adb shell dumpsys activity top` 查看页面信息
3. 新建 `xxxOperation` 类，继承 `DefaultOperation` 类，完善代码
4. `WhiteListManager` 类构造函数中注册自己的 `xxxOperation` 类
5. `MainActivity` 中写测试代码，运行调试
6. `README.md` 中完善信息

## 适配的机型
|厂商|手机型号|安卓版本|设置页面|自启动页面|休眠页面|贡献人|
|---|---|---|---|---|---|---|
|samsung|SM-G9250|Android 7.0(24)|√|√|√|[niulinguo]|
|OPPO|OPPO A57|Android 6.0.1(23)|√|√|√|[niulinguo]|
|Lenovo|Lenovo A3860|Android 5.1(22)|√|√|√|[niulinguo]|


[niulinguo]: https://github.com/niulinguo