
# 1 目的
整合Spring mvc，减少web层配置以及代码开发。
# 2 内容
## 2.1 控制器（controller）
说到 Controller，相信大家都不陌生，它可以很方便地对外提供数据接口。它的定位，我认为是「不可或缺的配角」，说它不可或缺是因为无论是传统的三层架构
还是现在的COLA架构，Controller 层依旧有一席之地，说明他的必要性；说它是配角是因为 Controller 层的代码一般是不负责具体的逻辑业务逻辑实现，但
是它负责接收和响应请求。
控制器功能：
* 接收请求并解析参数
* 调用 Service 执行具体的业务代码（可能包含参数校验）
* 捕获业务逻辑异常做出反馈
* 业务逻辑执行成功做出响应
## 2.1 规范api接口日志打印
todo
## 2.2 统一返回结果
统一返回值类型无论项目前后端是否分离都是非常必要的，方便对接接口的开发人员更加清晰地知道这个接口的调用是否成功，使用一个 状态码、状态信息就能清楚
地了解接口调用情况。
## 2.3 统一返回处理
todo
## 2.4 统一异常处理
消灭95%以上的 try catch 代码块，以优雅的 Assert(断言) 方式来校验业务的异常情况，只关注业务逻辑，而不用花费大量精力写冗余的 try catch 代码
块。
注：状态枚举结合断言处理异常代码十分优雅，但是判断都是以异常结束，对比检查判断效率略低。
附：Java类库中定义的可以通过预检查方式规避的 RuntimeException 异常不应该通过 catch 的方式来处理，比如：NullPointerException，
IndexOutOfBoundsException 等等。--《阿里巴巴开发规范手册》
正例：if (obj != null) {…}
反例：try { obj.method(); } catch (NullPointerException e) {…}
## 2.5 统一入参处理
JSR-303
# 3 依赖整理
依赖名称 | 功能 | 作用域 | 是否向上传递 |
------ | ---- | ---- | ---- |
`component-dto` | 定义了`DTO`格式，包括分页 | provided| 否 |
`component-exception` | 定义了异常格式，<br>主要有`BizException`和`SysException` | provided | 否 |
`component-util` | 工具类 | provided | 否 |
`spring-boot-starter-web` | spring boot web | compile | 是 |
`spring-boot-configuration-processor` | 自动配置引擎 | compile | 是 |
`hutool-all` | 工具包 | provided | 否 |
`lombok` | 注解包 | provided | 否 |
`jsoup` | xss攻击 | provided | 否 |
# 4 功能
包路径 | 功能 | 说明 |
------ | ---- | ---- |
`com.xqin.component.modules.apilog` | 统一打印调用api接口日志 | 用aop方式 |
`com.xqin.component.modules.web` | 定义了统一异常 | 用aop |
`com.xqin.component.modules.xss` | xss攻击处理 |