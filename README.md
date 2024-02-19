# 这是一个学习SpringBoot入门项目

## 版本
* jdk 17
* Spring Boot 3.1.2
* Mybatis plus 3.5.3.2

## 技术栈
* Mybatis
* Mybatis-Plus
* Swagger2
* Lombak
* jackjson
* Druid                           数据库连接池实现
* Redis
* RabbitMQ
* tomcat
* Spring Security                 安全认证和授权
* Spring Boot Admin               管理和监控Spring Boot应用程序
* Spring Boot Actuator            提供生产级的应用特性，如监控和度量
* Spring Boot DevTools            提高开发者的开发效率
* Spring Boot Web                 开发Web应用程序，包括RESTful应用程序
* Spring Boot Test                测试Spring Boot引用过程序
* Spring Boot Mail                发送邮件
* Spring Boot Quartz              定时任务
* Spring Boot JPA                 Java Persistence API（JPA）访问数据库
* Spring Boot Data Redis          Redis访问键值对数据
* Spring Boot Data MongoDB        MongoDB访问文档数据库
* Spring Boot Data Elasticsearch  Elasticsearch进行全文搜索
* Spring Boot Data JDBC           JDBC访问数据库
* Spring Boot Data REST           RESTful API访问数据
* Spring Boot Data Neo4j          Neo4j访问图数据库
* Spring Boot Data Solr           Apache Solr进行全文搜索
* Spring Boot Data LDAP           LDAP访问目录服务
* Spring Boot Data Cassandra      Cassandra访问列存储数据库
* Spring Boot Data Couchbase      Couchbase访问文档数据库。



## 项目结构

```
lyc.springboot.demo
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── lyc
│   │   │           └── springboot
│   │           └── demo
│   │               └── common
│   │                   └── api
│   │                   └── config
│   │                   └── constant
│   │                   └── exception
│   │                   └── utils
│   │               └── config
│   │               └── Log
│   │               └── controller
│   │               └── dao
│   │               └── dto
│   │               └── entity
│   │               └── mapper
│   │               └── service
│   │               └── service.impl
│   │               └── hadnler
│   │                   └── 
│   │   └── resources
│   │       └── application.yml
│   └── test
│       └── java
│           └── com
│               └── lyc
│                   └── springboot
│                       └── demo
│                           └── LycApplicationTests.java
└── README.md

```

##  项目结构
* common
  * BaseResponse
    * 统一响应返回
  * JsonResult
    * 统一响应返回
* service层 
  * 用于**通用业务逻辑**
* controller层
  * 用于**对外暴露接口**
* dao层
  * 用于**数据库操作**
* entity层 
  * 实体类
* mapper层 
  * 用于**数据库操作**
* service.impl层 
  * service**接口实现类**
* dto层
  *  用于**数据传输**
* config
  *  用于**配置文件**
* utils类
  *  工具类
* exception类
  *  异常类
* 切面类
  *  日志切面

User实体类 -> UserController ->  UserService.interface ->  UserServiceImpl.java -> UserMapper.interface -> UserMapper.xml

在一个典型的Spring Boot和MyBatis的项目中，这些类和接口通常位于以下的层：

1. User（实体类）：这是一个模型类，代表了你的业务领域中的一个实体。它通常位于模型层（Model）。

2. UserController：这是一个控制器类，负责处理HTTP请求和返回HTTP响应。它通常位于控制器层（Controller）。

3. UserService（接口）：这是一个服务接口，定义了一些业务操作。它通常位于服务层（Service）。

4. UserServiceImpl（类）：这是UserService接口的一个实现类，提供了具体的业务逻辑。它也位于服务层（Service）。

5. UserMapper（接口）：这是一个MyBatis的映射器接口，定义了一些与数据库交互的操作。它通常位于数据访问层（DAO或Repository）。

6. UserMapper.xml：这是一个MyBatis的映射文件，提供了UserMapper接口中定义的操作的SQL查询。它也位于数据访问层（DAO或Repository）。

7. UserDTO（Data Transfer Object）通常用于 服务层 和 控制器层 之间的数据传输。它通常包含了客户端需要的数据，而不是数据库中的所有数据。这样可以减少不必要的数据传输，提高应用程序的性能。 在分层架构中，UserDTO通常位于服务层（Service）和控制器层（Controller）之间。 服务层 会从数据库中获取数据（通常是User实体），然后将这些数据转换为UserDTO，并传递给控制器层。控制器层然后将UserDTO转换为HTTP响应。


这种分层的架构可以使你的代码更加模块化，更容易测试和维护。每一层都有其特定的职责，这样可以降低各层之间的耦合度，提高代码的可重用性。


## 项目依赖
pom.xml

## 项目配置

* 数据库配置
>application.properties

* 切换环境
>src/main/resources/application.yml

开发环境
```yaml
spring:
  profiles:
    active:
      - dev
```  
生效配置：
>src/main/resources/application-dev.yml

产线环境
```yaml
spring:
  profiles:
    active:
      - prod
```
生效配置：
>src/main/resources/application-prod.yml

## 启动
* dev
```bash
mvn spring-boot:run
```

##  打jar包

```bash
mvn clean package -Dmaven.test.skip=true
```
* 生成jar包在target目录下
>target/lyc.springboot.demo-0.0.1-SNAPSHOT.jar

## 运行jar包

```bash
java -jar lyc.springboot.demo.jar
```

# restfull API

>com.example.lyc.springboot.demo.controller.UserController

## GET

* 示例1：
```java
   /**
     * @author: liyinchi
     * @description 通过获取所有用户信息
     * @mark
     * */
    @Operation(summary = "获取所有用户", description = "返回所有用户的列表")
    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
    public BaseResponse<List<UserDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDTO> userDTOs = users.stream().map(this::convertToDto).collect(Collectors.toList());
        log.info("=======getAllUsers: " + userDTOs);
        return BaseResponse.success(userDTOs);
    }
```


* 返回数据 List
  
  <img width="400" height="400" alt="image" src="https://github.com/liyinchigithub/springboot-learn/assets/19643260/7758a36e-703f-40e0-a58e-4de8d6d96267">

* 示例2：
```java
   /**
     * @author: liyinchi
     * @description 通过ID获取用户信息
     * @mark    /getUserById/{id}
     * */
    @Operation(summary = "通过ID获取用户", description = "返回指定ID的用户")
    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public BaseResponse<UserDTO> getUserById(@Parameter(description = "用户ID", required = true) @PathVariable int id) {
        User user = userService.getUserById(id);
        log.info("=======getUserById user:{}", user);
        return BaseResponse.success(convertToDto(user));
    }
```
* 返回数据 List
  
<img width="400" height="400" alt="image" src="https://github.com/liyinchigithub/springboot-learn/assets/19643260/5d0ebcf0-5a69-4b70-9d6a-fcd9ea79a0ba">

* 示例3：
```java
     /**
     * @author: liyinchi
     * @description 通过ID获取用户信息
     * @mark    ?id=1&name=liyinchi
     * */
    @Operation(summary = "通过ID参数获取用户", description = "返回指定ID的用户")
    @RequestMapping(value = "/getUserByIdParam", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public BaseResponse<UserDTO> getUserByIdParam(@Parameter(description = "用户ID", required = true) @RequestParam("id") int id) {
        User user = userService.getUserById(id);
        log.info("=======getUserByIdParam user:{}", user);
        return BaseResponse.success(convertToDto(user));
    }
```

* 返回数据 List
  
<img width="400" height="400" alt="image" src="https://github.com/liyinchigithub/springboot-learn/assets/19643260/00b34bc9-9596-49b8-943e-2dc4cf356e62">


## 分页查询

在Spring Boot中，我们可以使用Spring Data JPA的分页功能来实现分页查询。以下是一个简单的例子：


（1）首先，你需要在UserMapper接口中添加一个新的方法，该方法接收一个RowBounds对象和一个排序字段名作为参数，并返回一个List<User>对象：

* UserMapper.java

```java
public interface UserMapper {
    // 其他方法...

    // 分页查询
    List<User> getAllUsers(int offset, int limit, String sortField);
}
```

（2）然后，在你的UserMapper.xml文件中，你需要添加一个新的SQL查询，该查询使用ORDER BY子句来排序结果，并使用LIMIT和OFFSET子句来实现分页：

* UserMapper.xml

```xml
<select id="getAllUsers" resultType="com.example.lyc.springboot.demo.entity.User">
  SELECT * FROM User ORDER BY ${sortField} LIMIT #{limit} OFFSET #{offset}
</select>
```

（3）接下来，在UserService接口中，你需要添加一个新的方法，该方法接收页码、页大小和排序字段名作为参数，并返回一个List<User>对象：

* UserService.java

```java
    public interface UserService {
    // 分页查询
    List<User> getAllUsers(int page, int size, String sortField);
                                }
```

（4）然后，在你的UserServiceImpl类中实现这个方法：

* UserServiceImpl.java

```java
 @Override
public List<User> getAllUsers(int page, int size, String sortField) {
        log.debug("page" + page + " size" + size + " sortField" + sortField);
        // 检查page和size参数的有效性
        if (page < 0 || size <= 0) {
        throw new IllegalArgumentException("Page must be non-negative and size must be positive");
        }

        // 检查sortField参数的有效性
        if (sortField == null || sortField.isEmpty()) {
        sortField = "id";  // 使用一个默认的排序字段
        }

        int offset = page * size;
        return userMapper.getAllUsers(offset, size, sortField);
                                                                    }
```

（5）最后，在你的UserController类中，你可以添加一个新的方法来处理分页和排序的请求：

* UserController.java

```java
    /**
 * @author: liyinchi
 * @description 分页查询
 * @param page 页码
 * @param size 每页显示条数
 * @return object
 * */
@GetMapping("/getAllUsersPagedSorted")
public BaseResponse<List<UserDTO>> getAllUsersPagedSorted(@RequestParam int page, @RequestParam int size, @RequestParam String sortField) {
        List<User> users = userService.getAllUsers(page, size, sortField);
        List<UserDTO> userDTOs = users.stream().map(this::convertToDto).collect(Collectors.toList());
        return BaseResponse.success(userDTOs);
        }
```

在这个例子中，客户端可以通过发送一个GET请求到/getAllUsersPagedSorted，并在请求参数中指定page，size和sortField来获取分页和排序的用户列表。

例如，发送一个请求到/getAllUsersPagedSorted?page=0&size=10&sortField=userName将返回第一页的10个用户，并按照userName字段进行排序，例如：id。

## POST

* 示例1：

```java
   /**
     * @author: liyinchi
     * @description 新增用户
     * @mark json
     * */
    // 定义一个处理POST请求的方法，路径为"/addUser"，返回的数据类型为JSON
    @Operation(summary = "新增用户", description = "通过JSON数据新增用户")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    // 该方法接收一个参数，即UserDTO对象，这个对象是通过@RequestBody注解从请求体中获取的
    public BaseResponse<UserIdResponseDTO> addUser(@Parameter(description = "用户数据", required = true) @RequestBody UserDTO userDto) {
        // 调用convertToEntity方法，将UserDTO对象转换为User对象
        User user = convertToEntity(userDto);
        // 调用userService的addUser方法，将User对象添加到数据库中
        int newUserId = userService.addUser(user);
        // 记录日志，输出添加的User对象的信息
        log.info("=======addUser:{}", user);
        // 返回一个表示操作成功的BaseResponse对象
        return BaseResponse.success(new UserIdResponseDTO(newUserId));
    }
```

* 返回数据 Object


<img width="400" height="400" alt="image" src="https://github.com/liyinchigithub/springboot-learn/assets/19643260/50cf6d7a-f670-46bf-969e-4fc7512cc9d0">

* 示例2：

```java
    /**
     * @author: liyinchi
     * @description 新增用户
     * @mark 表单
     *
     * */
    // 定义一个处理POST请求的方法，路径为"/addUserFormData"，返回的数据类型为JSON
    @Operation(summary = "新增用户（表单）", description = "通过表单数据新增用户")
    @RequestMapping(value = "/addUserFormData", method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    // 该方法接收三个参数，分别是id，userName和password，这些参数都是通过@RequestParam注解从请求中获取的
    public BaseResponse<UserIdResponseDTO> addUserFormData(@Parameter(description = "用户名", required = true) @RequestParam("userName") String userName,
                                                           @Parameter(description = "密码", required = true) @RequestParam("password") String password){
        // 创建一个新的UserDTO对象
        UserDTO userDto = new UserDTO();
        // 将从请求中获取的参数设置到UserDTO对象中
        userDto.setUserName(userName);
        userDto.setPassword(password);
        // 调用convertToEntity方法，将UserDTO对象转换为User对象
        User user = convertToEntity(userDto);
        // 记录日志，输出添加的User对象的信息
        log.info("=======addUserFormData:{}", user);
        // 调用userService的addUser方法，将User对象添加到数据库中
        int newUserId = userService.addUser(user);
        // 返回一个表示操作成功的BaseResponse对象
        return BaseResponse.success(new UserIdResponseDTO(newUserId));
    }
```

* 返回数据 Object
  
<img width="400" height="400" alt="image" src="https://github.com/liyinchigithub/springboot-learn/assets/19643260/ba339d0a-bad1-4d7b-ae50-1a90061494eb">




## PUT

* 示例1：

```java
/**
     * @author: liyinchi
     * @description 更新用户
     * @mark json
     * */
    @Operation(summary = "更新用户", description = "通过JSON数据更新用户")
    // 定义一个处理PUT请求的方法，路径为"/updateUser"，返回的数据类型为JSON
    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT,produces = "application/json; charset=UTF-8")
    // 该方法接收一个参数，即UserDTO对象，这个对象是通过@RequestBody注解从请求体中获取的
    public BaseResponse<UpdateUserResponseDTO> updateUser(@Parameter(description = "用户数据", required = true) @RequestBody UserDTO userDto) {
        // 调用convertToEntity方法，将UserDTO对象转换为User对象
        User user = convertToEntity(userDto);
        // 调用userService的updateUser方法，将User对象的信息更新到数据库中
        int updates = userService.updateUser(user);
        // 记录日志，输出更新的User对象的信息
        log.info("=======updateUser:{}", user);
        // 返回一个表示操作成功的BaseResponse对象
        return BaseResponse.success(new UpdateUserResponseDTO(user.getId(), updates));
    }

```

* 返回数据

  <img width="400" height="400" alt="image" src="https://github.com/liyinchigithub/springboot-learn/assets/19643260/69744455-2793-40b9-9372-e8e8204839c2">



## DELETE

* 示例1：

```java
/**
     * @author: liyinchi
     * @description 删除用户
     * @param request object {"id":4}
     * @return object
     * */
    // 定义一个处理DELETE请求的方法，路径为"/deleteUser"，返回的数据类型为JSON
    @Operation(summary = "删除用户", description = "通过JSON数据删除用户")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE,produces = "application/json; charset=UTF-8")
    // 该方法接收一个参数，即DeleteUserRequestDTO对象，这个对象是通过@RequestBody注解从请求体中获取的
    public BaseResponse<DeleteUserResponseDTO>  deleteUser(@Parameter(description = "删除用户请求数据", required = true)  @RequestBody DeleteUserRequestDTO request) {
        // 调用userService的deleteUser方法，根据请求中的id删除数据库中的用户
        int updates = userService.deleteUser(request.getId());// 获取请求参数id值
        // 记录日志，输出删除的用户的请求信息
        log.info("=======deleteUser:{}", request);
        // 返回一个表示操作成功的BaseResponse对象
        return BaseResponse.success(new DeleteUserResponseDTO(request.getId(), updates));
    }
```

* 返回数据 

<img width="400" height="400"  alt="image" src="https://github.com/liyinchigithub/springboot-learn/assets/19643260/a4181ff1-5a87-492b-8230-4af94510e40d">



* 示例2：

```java
  /**
     * @author: liyinchi
     * @description 删除用户
     * @mark  请求参数在URI中
     * @param id 用户ID
     * @return String
     *
     * */
    @Operation(summary = "通过ID删除用户", description = "删除指定ID的用户")
    @RequestMapping(value = "/deleteUserPath/{id}", method = RequestMethod.DELETE,produces = "application/json; charset=UTF-8")
    public BaseResponse<UserIdResponseDTO> deleteUserPath(@Parameter(description = "用户ID", required = true) @PathVariable int id) { // id在URI中
        userService.deleteUser(id);
        log.info("=======deleteUserPath:{}", id);
        return BaseResponse.success(new UserIdResponseDTO(id));
    }
```

* 返回数据 

<img width="400" height="400"  alt="image" src="https://github.com/liyinchigithub/springboot-learn/assets/19643260/c889e1b4-9319-4ba9-9adc-d83cfed60f55">


* 示例3：

```java
 /**
     * @author: liyinchi
     * @description 删除用户
     * @mark 请求参数在URI中 ?id=
     * @param id 用户ID
     * @return object
     *
     * */
    @Operation(summary = "通过ID参数删除用户", description = "删除指定ID的用户")
    @RequestMapping(value = "/deleteUserParam", method = RequestMethod.DELETE,produces = "application/json; charset=UTF-8")
    public BaseResponse<UserIdResponseDTO> deleteUserParam(@Parameter(description = "用户ID", required = true) @RequestParam("id") int id) { // id在URI中
        userService.deleteUser(id);
        log.info("=======deleteUserParam:{}", id);
        return BaseResponse.success(new UserIdResponseDTO(id));
    }

```

* 返回数据 

<img width="400" height="400" alt="image" src="https://github.com/liyinchigithub/springboot-learn/assets/19643260/36db82b5-e195-4bf3-a4c7-f3551b30f47f">



# 常用命令

```bash
mvn clean package -Dmaven.test.skip=true
mvn clean install
```

# swagger接口文档

>http://localhost:8088/swagger-ui.html

> http://localhost:8088/swagger-ui/index.html?urls.primaryName=public#/

<img width="1429" alt="image" src="https://github.com/liyinchigithub/lyc.springboot.demo/assets/19643260/eea042be-b835-4434-be4a-c98930333e07">


# 全局异常处理

>com.lyc.springboot.demo.common.exception.GlobalExceptionHandler

##  业务异常（枚举异常）
>com.lyc.springboot.demo.common.exception.BusinesusinessMsgEnums

## 参数缺失
>com.lyc.springboot.demo.common.exception.GlobalExceptionHandler

```java
 /**
     * 缺少请求参数异常
     * @param ex HttpMessageNotReadableException
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public BaseResponse handleHttpMessageNotReadableException(MissingServletRequestParameterException ex) {
        log.error("缺少请求参数，{}", ex.getMessage());
        return new BaseResponse(400, "缺少必要的请求参数");
    }
```


##  处理空指针异常
>com.lyc.springboot.demo.common.exception.GlobalExceptionHandler

```java
 /**
     * 系统异常 预期以外异常
     * @param ex
     * 项目中，我们一般都会比较详细的去拦截一些常见异常，拦截 Exception 虽然可以一劳永逸，但是不利于我们去排查或者定位问题。
     * 实际项目中，可以把拦截 Exception 异常写在 GlobalExceptionHandler 最下面，如果都没有找到，最后再拦截一下 Exception 异常，保证输出信息友好
     *
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleUnexpectedServer(Exception ex) {
        log.error("系统异常：", ex);
        return new BaseResponse(500, "系统未知异常，请联系管理员");
    }

```

##  所有不可知的异常(不推荐)
>com.lyc.springboot.demo.common.exception.GlobalExceptionHandler

```java
  /**
     * @Description: 所有不可知的异常
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        // 这里可以记录日志，发送通知等
        return new ResponseEntity<>("请求参数错误", HttpStatus.BAD_REQUEST);
    }
```


## 处理数组越界异常

>com.lyc.springboot.demo.common.exception.GlobalExceptionHandler
```java
  /**
     * 处理数组越界异常
     * */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleIndexOutOfBoundsException(IndexOutOfBoundsException ex) {
        log.error("数组越界异常，{}", ex.getMessage());
        return new BaseResponse(500, "数组越界异常了");
    }
```


## 一劳永逸异常
>com.lyc.springboot.demo.common.exception.GlobalExceptionHandler

```java
 /**
     * 系统异常（预期意外）
     * @param ex
     * 项目中，我们一般都会比较详细的去拦截一些常见异常，拦截 Exception 虽然可以一劳永逸，但是不利于我们去排查或者定位问题。
     * 实际项目中，可以把拦截 Exception 异常写在 GlobalExceptionHandler 最下面，如果都没有找到，最后再拦截一下 Exception 异常，保证输出信息友好
     *
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleUnexpectedServer(Exception ex) {
        log.error("系统异常：", ex);
        return new BaseResponse(500, "系统未知异常，请联系管理员");
    }
```


# 全局返回结果处理

>com.lyc.springboot.demo.common.api.BaseResponse

## 处理成功返回结果

```java
   public BaseResponse() {
        this.code = 0;
        this.message = "success";
    }
```


## 处理失败返回结果

```java
```


# 统一日志处理

>com.lyc.springboot.demo.common.config.LogAspect



# 文件上传

>com/example/lyc/springboot/demo/controller/UploadController.java

接收上传文件，存放在指定路径下

```java
**
 * 文件上传
 * 限制上传大小、文件格式、存放位置、重命名上传文件
 * */
@Slf4j
@RestController
@RequestMapping("/v1/fileUpload")
@Schema(name="文件上传", description="文件上传")
@Tag(name = "文件上传")
public class UploadController {
    private static final String UPLOAD_DIR = "./upload";

    @PostMapping("/upload")
    public BaseResponse<String> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId) {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        // 限制上传文件大小
        if (file.getSize() > 1024 * 1024 * 5) {
            return new BaseResponse<>(1, "File size too large", null);
        }
        // 限制上传文件格式
        if (!"jpg".equals(fileExtension) && !"png".equals(fileExtension)) {
            return new BaseResponse<>(1, "Invalid file format", null);
        }

        // Rename the file
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String newFilename = userId + "_" + timestamp + "_" + originalFilename;

        // Save the file to the server
        Path filePath = Paths.get(UPLOAD_DIR, newFilename);
        try {
            Files.write(filePath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return new BaseResponse<>(1, "Failed to save file", null);
        }

        return new BaseResponse<>(0, "File uploaded successfully", newFilename);
    }
}
```
![image](https://github.com/liyinchigithub/springboot-learn/assets/19643260/d2957a95-cc9e-4e93-9602-8dc251d9d90f)



接收上传文件，存放在阿里云存储上

```java
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.lyc.springboot.demo.commons.api.BaseResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
public class UploadController {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    @PostMapping("/upload")
    public BaseResponse<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        String fileExtension = filename.substring(filename.lastIndexOf(".") + 1);
        if (!"jpg".equals(fileExtension) && !"png".equals(fileExtension)) {
            return new BaseResponse<>(1, "Invalid file format", null);
        }

        // Create an OSSClient instance
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // Upload the file to OSS
            ossClient.putObject(bucketName, filename, new ByteArrayInputStream(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
            return new BaseResponse<>(1, "Failed to upload file", null);
        } finally {
            // Shut down the OSSClient
            ossClient.shutdown();
        }

        return new BaseResponse<>(0, "File uploaded successfully", filename);
    }
}

```

# 生成工具

## 快速实现实体类、mapper、数据库表字段

>com/example/lyc/springboot/demo/util/MybatisGenerator.java

![image](https://github.com/liyinchigithub/springboot-learn/assets/19643260/878198bf-69df-4294-8df9-126b4e74f594)




# 切面

## 日志切面

>com/example/lyc/springboot/demo/handler/LogAspectHandler.java


```yml
@Pointcut：定义一个切面，即上面所描述的关注的某件事入口。
@Before：在做某件事之前做的事。
@After：在做某件事之后做的事。
@AfterReturning：在做某件事之后，对其返回值做增强处理。
@AfterThrowing：在做某件事抛出异常时，处理。
```

* @Before 注解

指定的方法在切面切入目标方法之前执行

可以做一些 log 处理，也可以做一些信息的统计，比如获取用户的请求 url 以及用户的 ip 地址等等
```java
@Aspect
@Slf4j
@Component
public class LogAspectHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 定义一个切面，拦截com.itcodai.course09.controller包和子包下的所有方法
     */
    @Pointcut("execution(* com.example.lyc.springboot.demo..*.*(..))")
    public void pointCut() {}

    /**
     * 在上面定义的切面方法之前执行该方法
     * @param joinPoint jointPoint
     */
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("====doBefore方法进入了====");
        // 获取签名
        Signature signature = joinPoint.getSignature();
        // 获取切入的包名
        String declaringTypeName = signature.getDeclaringTypeName();
        // 获取即将执行的方法名
        String funcName = signature.getName();
        log.info("即将执行方法为: {}，属于{}包", funcName, declaringTypeName);

        // 也可以用来记录一些信息，比如获取请求的url和ip
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            // 获取请求url
            String url = request.getRequestURL().toString();
            // 获取请求ip
            String ip = request.getRemoteAddr();
            log.info("用户请求的url为：{}，ip地址为：{}", url, ip);
        } else {
            log.info("Not in a request context");
        }
    }

}
```
<img width="1351" alt="image" src="https://github.com/liyinchigithub/springboot-learn/assets/19643260/613174c2-6a0c-4812-beaa-0e9e9d60882d">




* @After 注解 

**指定的方法在切面切入目标方法之后执行**

也可以做一些完成某方法之后的 log 处理
```java
   /**
   * 在上面定义的切面方法之后执行该方法
   * @param joinPoint jointPoint
   */
  @After("pointCut()")
  public void doAfter(JoinPoint joinPoint) {
          log.info("====doAfter方法进入了====");
          Signature signature = joinPoint.getSignature();
          String method = signature.getName();
          log.info("方法{}已经执行完", method);
          }
```

<img width="1340" alt="image" src="https://github.com/liyinchigithub/springboot-learn/assets/19643260/45bc19eb-30ba-481b-8f78-6721daf1f8da">


* @AfterReturning 注解

用来捕获切入方法执行完之后的返回值，对返回值进行业务逻辑上的增强处理

**[需要注意的是]**
在 @AfterReturning注解 中，属性 returning 的值必须要和参数保持一致，否则会检测不到。该方法中的第二个入参就是被切方法的返回值，在 doAfterReturning 方法中可以对返回值进行增强，可以根据业务需要做相应的封装。

```java
    /**
     * 在上面定义的切面方法返回后执行该方法，可以捕获返回对象或者对返回对象进行增强
     * @param joinPoint joinPoint
     * @param result result
     */
    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {

        Signature signature = joinPoint.getSignature();
        String classMethod = signature.getName();
        log.info("方法{}执行完毕，返回参数为：{}", classMethod, result);
        // 实际项目中可以根据业务做具体的返回值增强
        log.info("对返回参数进行业务上的增强：{}", result + "增强版");
    }
```

<img width="1343" alt="image" src="https://github.com/liyinchigithub/springboot-learn/assets/19643260/b66bd351-27ae-4f94-ba30-87cf72d55d7e">


* @AfterThrowing 注解

当被切方法执行时抛出异常时，会进入 @AfterThrowing 注解的方法中执行，在该方法中可以做一些异常的处理逻辑。

要注意的是 throwing 属性的值必须要和参数一致，否则会报错。该方法中的第二个入参即为抛出的异常。
```java
/**
     * 在上面定义的切面方法执行抛异常时，执行该方法
     * @param joinPoint jointPoint
     * @param ex ex
     */
    @AfterThrowing(pointcut = "pointCut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        // 处理异常的逻辑
        log.info("执行方法{}出错，异常为：{}", method, ex);
    }
```

annotation() 方式是针对某个注解来定义切面，比如我们对具有@GetMapping注解的方法做切面，可以如下定义切面：
```java
@Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
public void annotationCut() {}
```

# 静态资源

Spring Boot 的默认静态目录为 resources/static

### 配置静态资源路径

第一种方式：application.yml 文件中添加配置

>src/main/resources/application-dev.yml

图片
```yaml
spring:
  resources:
    static-locations: [classpath:/static/]
```

第二种方式：代码中
>com/example/lyc/springboot/demo/config/MvcConfig.java
```java
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:/absolute/path/to/your/directory/");
    }
}

```

接口
>com/example/lyc/springboot/demo/controller/ImageController.java



#### 配置多媒体音频文件

代码中
>com/example/lyc/springboot/demo/config/MvcConfig.java

```java
@Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
  registry.addResourceHandler("/files/**")
  .addResourceLocations("file:./upload/");
  registry.addResourceHandler("/mp3/**")
  .addResourceLocations("file:/src/main/resource/media/");
}
```

图片+音频

>src/main/resources/application-dev.yml

```yaml

spring:
  #  配置静态资源路径
  resources:
    static-locations:
      - classpath:/static/
      - classpath:/media/
```



配置pom.xml

```xml
 <resources>
        <resource>
            <directory>src/main/resources</directory>
        </resource>
        <resource>
            <directory>src/main/resources/media</directory>
        </resource>
    </resources>
```
如果你正在使用Spring Boot的内置Tomcat服务器，那么你可能需要将src/main/resources/media目录添加到你的项目的资源路径中。你可以在你的pom.xml文件中添加以下内容：


#### 音频在线可播放

```java
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;

@GetMapping("/media/{filename:.+}")
public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

    Resource file = new ClassPathResource("src/main/resources/media/" + filename);
    try {
        file.getInputStream(); // check if file exists

        // Set the content type to audio/mpeg (for .mp3 files)
        // Adjust this to match the type of audio file you are serving (e.g., audio/ogg for .ogg files)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("audio/mpeg"));
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"");

        return ResponseEntity.ok().headers(headers).body(file);
    } catch (IOException e) {
        log.error("File not found: {}", filename, e);
        return ResponseEntity.notFound().build();
    }
}

```

在这个示例中，我们设置了 Content-Type 头为 audio/mpeg，这是 .mp3 文件的 MIME 类型。

你需要根据你的音频文件的实际类型来调整这个值。

我们还设置了 Content-Disposition 头为 inline，这意味着浏览器应尝试在页面上直接播放音频，而不是下载文件。



#### 音频下载


如果你想让音频文件被下载而不是在线播放，你可以将 Content-Disposition 头的值从 inline 改为 attachment。

这将提示浏览器下载文件而不是尝试在页面上播放它。

```java
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;

@GetMapping("/media/{filename:.+}")
public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

    Resource file = new ClassPathResource("src/main/resources/media/" + filename);
    try {
        file.getInputStream(); // check if file exists

        // Set the content type to audio/mpeg (for .mp3 files)
        // Adjust this to match the type of audio file you are serving (e.g., audio/ogg for .ogg files)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("audio/mpeg"));
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"");

        return ResponseEntity.ok().headers(headers).body(file);
    } catch (IOException e) {
        log.error("File not found: {}", filename, e);
        return ResponseEntity.notFound().build();
    }
}
```




# 事务

## UserMapper.xml


```xml

```

## UserServiceImpl.java

```java

```

* SQL异常不能被识别回滚
SpringBoot 默认的事务规则是遇到 **运行异常（RuntimeException）** 和）**程序错误（Error）** 才会回滚。
比如抛出的 RuntimeException 就没有问题，但是抛出 **SQLException** 就无法回滚了。
针对非运行时异常，如果要进行事务回滚的话，可以在 **@Transactional** 注解中使用 **rollbackFor** 属性来指定异常，比如 **@Transactional(rollbackFor = Exception.class)**，这样就没有问题了，所以在实际项目中，一定要指定异常。

* 异常被 ”吃“ 掉
这个标题很搞笑，异常怎么会被吃掉呢？
还是回归到现实项目中去，我们在处理异常时，有两种方式：
  **要么抛出去，让上一层来捕获处理；**
  **要么把异常 try catch 掉，在异常出现的地方给处理掉。**
  就因为有这中 try…catch，所以导致异常被 ”吃“ 掉，事务无法回滚。

那这种怎么解决呢？
  **直接往上抛，给上一层来处理即可，千万不要在事务中把异常自己 ”吃“ 掉。**


* 事务范围这个东西比上面两个坑埋的更深！

举个实际的场景，比如：
一个数据库中，针对某个用户，只有一条记录，下一个插入动作过来，会先判断该数据库中有没有相同的用户，如果有就不插入，就更新，没有才插入，所以理论上，数据库中永远就一条同一用户信息，不会出现同一数据库中插入了两条相同用户的信息。
但是在压测时，就会出现上面的问题，数据库中确实有两条同一用户的信息，分析其原因，在于事务的范围和锁的范围问题。


那这种怎么解决呢？

**根本原因**：
也就是说，在加锁的那部分代码执行完之后，锁释放掉了，但是事务还没结束，此时另一个线程进来了，事务没结束的话，第二个线程进来时，数据库的状态和第一个线程刚进来是一样的。
即由于mysql Innodb引擎的默认隔离级别是可重复读（在同一个事务里，SELECT的结果是事务开始时时间点的状态），线程二事务开始的时候，线程一还没提交完成，导致读取的数据还没更新。
第二个线程也做了插入动作，导致了脏数据。

这个问题可以避免，第一，把事务去掉即可（不推荐）；第二，在调用该 service 的地方加锁，保证锁的范围比事务的范围大即可。


* 如何控制事务范围和锁范围？

在Spring Boot中，事务的范围通常由@Transactional注解的方法定义。

当你在一个方法上使用@Transactional注解，Spring会在该方法开始时启动一个新的事务，并在方法结束时提交事务。

如果方法抛出了未捕获的异常，Spring会回滚事务,这就是事务的范围。


# 监听器

>com/example/lyc/springboot/demo/listener


监听器 --> 监听事件

## （一）应用监听

### 1.监听Servlet上下文对象

略

### 2.监听HTTP会话 Session对象

* 监听器还有一个比较常用的地方就是用来监听 session 对象，来获取在线用户数量，现在有很多开发者都有自己的网站，监听 session 来获取当前在下用户数量是个很常见的使用场景。

（1）**MyHttpSessionListener.java**
```java
package com.example.lyc.springboot.demo.listener;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 使用HttpSessionListener统计在线用户数的监听器
 * @author liyinchi
 * @date 2023/12/13
 */
@Component
public class MyHttpSessionListener implements HttpSessionListener {

    private static final Logger logger = LoggerFactory.getLogger(MyHttpSessionListener.class);

    /**
     * 记录在线的用户数量
     */
    public Integer count = 0;

    @Override
    public synchronized void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.info("新用户上线了");
        count++;
        httpSessionEvent.getSession().getServletContext().setAttribute("count", count);
    }

    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logger.info("用户下线了");
        count--;
        httpSessionEvent.getSession().getServletContext().setAttribute("count", count);
    }
}
```
可以看出，首先该监听器需要实现 HttpSessionListener 接口，然后重写 sessionCreated 和 sessionDestroyed 方法，在 sessionCreated 方法中传递一个 HttpSessionEvent 对象，然后将当前 session 中的用户数量加1，sessionDestroyed 方法刚好相反。

* 写一个 Controller 来测试一下

（2）**TestController.java**

```java
@RestController
@RequestMapping("/listener")
public class onlineController {

  @GetMapping("/total2")
  public String getTotalUser(HttpServletRequest request, HttpServletResponse response) {
    Cookie cookie;
    try {
      // 把sessionId记录在浏览器中
      cookie = new Cookie("JSESSIONID", URLEncoder.encode(request.getSession().getId(), "utf-8"));
      cookie.setPath("/");
      //设置cookie有效期为2天，设置长一点
      cookie.setMaxAge( 48*60 * 60);
      response.addCookie(cookie);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    Integer count = (Integer) request.getSession().getServletContext().getAttribute("count");
    return "当前在线人数：" + count;
  }
}
```

该处理逻辑是让服务器记得原来那个session

即 把原来的sessionId记录在浏览器中，下次再打开时，把这个 sessionId 传过去，这样服务器就不会重新再创建了。

重启一下服务器，在浏览器中再次测试一下，即可避免上面的问题。


### 3.监听客户端请求Servlet Request对象

* 使用监听器获取用户的访问信息

（1）**ServletRequestListener**

实现 ServletRequestListener接口，然后通过 request对象 获取一些信息。


```java
package com.example.lyc.springboot.demo.listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 使用ServletRequestListener获取访问信息
 * @author liyinchi
 * @date 2023/12/13
 */
@Component
public class MyServletRequestListener implements ServletRequestListener {

    private static final Logger logger = LoggerFactory.getLogger(MyServletRequestListener.class);

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        logger.info("session id为：{}", request.getRequestedSessionId());
        logger.info("request url为：{}", request.getRequestURL());

        request.setAttribute("name", "测试自定义事件监听器");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

        logger.info("request end");
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        logger.info("request域中保存的name值为：{}", request.getAttribute("name"));

    }

}

```

（2）**UserController**
* 接下来写一个 Controller 测试

```java
@GetMapping("/request")
public String getRequestInfo(HttpServletRequest request) {
    System.out.println("requestListener中的初始化的name数据：" + request.getAttribute("name"));
    return "success";
}
```


## （二）自定义监听

*  定义事件

1. 自定义事件

（1）**MyEvent.java**

>com/example/lyc/springboot/demo/event/MyEvent.java

```java
/**
 * 自定义事件
 * @author liyinchi
 * @date 2023/12/13
 */
@Getter
@Setter
public class MyEvent extends ApplicationEvent {

  private User user;

  public MyEvent(Object source, User user) {
    super(source);
    this.user = user;
  }

  // 省去get、set方法
}
```

*  自定义一个监听器

监听上面定义的 MyEvent 事件，自定义监听器需要实现 ApplicationListener 接口即可。重写 onApplicationEvent 方法)

（2）**MyEventListener.java**

>com/example/lyc/springboot/demo/event/MyEventListener.java

```java
/**
 * 自定义监听器，监听MyEvent事件
 * @author liyinchi
 * @date 2023/12/13
 */
@Slf4j
@Component
public class MyEventListener implements ApplicationListener<MyEvent> {
  @Override
  public void onApplicationEvent(MyEvent myEvent) {
    // 把事件中的信息获取到
    User user = myEvent.getUser();
    // 处理事件，实际项目中可以通知别的微服务或者处理其他逻辑等等
    log.info("MyEventListener", "用户名：" + user.getUserName());
    log.info("MyEventListener", "密码：" + user.getPassword());

  }
}
```

*  发布事件

（3）**UserServiceImple**

>/com/example/lyc/springboot/demo/service/impl/UserServiceImple.java


```java
 @Service
public class UserServiceImpl implements UserService {

    /**
     * 发布事件
     * @return
     */
    public User getUser2() {
        User user = new User(1, "测试", "123456", 0);
        // 发布事件
        MyEvent event = new MyEvent(this, user);
        applicationContext.publishEvent(event);
        return user;
    }
}
```

* 测试接口

**UserController**

最后，在 Controller 中写一个接口来测试一下：

>/com/example/lyc/springboot/demo/controller/UserController.java
> 
```java
@GetMapping("/request")
public String getRequestInfo(HttpServletRequest request) {
    log.info("requestListener中的初始化的name数据：" + request.getAttribute("name"));
    return "success";
}
```


# 拦截器

>com/example/lyc/springboot/demo/controller/Interceptor

拦截器的原理很简单，是AOP的一种实现，专门拦截对动态资源的后台请求，即拦截对控制层的请求。

使用场景比较多的是**判断用户是否有权限请求后台**，更拔高一层的使用场景也有，比如拦截器可以结合 websocket 一起使用，用来**拦截 websocket 请求**，然后做相应的处理等等。
**拦截器不会拦截静态资源**，Spring Boot 的默认静态目录为 resources/static，该目录下的静态页面、js、css、图片等等，不会被拦截（也要看如何实现，有些情况也会拦截，我在下文会指出）。

在 MVC（Model-View-Controller）架构中，拦截器（Interceptor）通常属于控制器（Controller）层。

它们主要用于处理跨越多个请求或控制器的行为，例如：**日志记录**、**身份验证**、**授权**以及**性能监控**等。

在 Spring 框架中，拦截器可以注册为 Spring Bean 并配置为拦截特定的 URL 模式，以在请求被实际的控制器处理**之前**或**之后**执行特定的操作。

* 使用拦截器很简单，只需要两步即可：**①定义拦截器**和**②配置拦截器**

（1）定义拦截器

>com/example/lyc/springboot/demo/controller/Interceptor/MyInterceptor.java

```java
@Configuration
public class MyInterceptorConfig extends WebMvcConfigurationSupport {

    /**
     * 用来指定静态资源不被拦截，否则继承WebMvcConfigurationSupport这种方式会导致静态资源无法直接访问
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
```



（2）配置拦截器

>com/example/lyc/springboot/demo/config/MyInterceptorConfig.java

```java

@Configuration
public class MyInterceptorConfig extends WebMvcConfigurationSupport {

  /**
   * 用来指定静态资源不被拦截，否则继承WebMvcConfigurationSupport这种方式会导致静态资源无法直接访问
   * @param registry
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
  }

  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    super.addResourceHandlers(registry);
  }
}
```

## 测试拦截器

>com/example/lyc/springboot/demo/controller/TestController.java

```java
@Controller
@RequestMapping("/interceptor")
public class InterceptorController {
    @RequestMapping("/test")
    public String test() {
        return "hello";
    }
}
```
<img width="1371" alt="image" src="https://github.com/liyinchigithub/springboot-learn/assets/19643260/880f0142-39fa-47d2-a036-21185d784077">


# Redis

## 阿里云云原生redis




## 本地安装redis 


（1）拉镜像
```shell
docker pull  redis
```
（2）启动容器
```shell
docker run --name my-redis -d -p 6379:6379 redis redis-server --protected-mode yes --requirepass 123456
```

## 配置redis

>src/main/resources/application.yml

```yaml
#redis相关配置
redis:
  database: 1 # 
  # 配置redis的主机地址，需要修改成自己的
  host: 127.0.0.1
  port: 6380   # 默认是6379
  password: 123456
  timeout: 10000
  jedis:
    pool:
      # 连接池中的最大空闲连接，默认值也是8。
      max-idle: 500
      # 连接池中的最小空闲连接，默认值也是0。
      min-idle: 50
      # 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)
      max-active: 1000
      # 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
      max-wait: 2000
```


## docker redis 查看ip和端口
```shell
docker inspect my-redis
```

如果Redis容器运行在本地，并且映射到了6379端口，可以使用以下命令来测试连接
```shell
redis-cli -h 127.0.0.1 -p 6379
```

```shell
nc -zv 127.0.0.1 6380
```



## 常用redis方法







## 分布式锁

## 分布式事务

## 分布式ID

## 分布式缓存

# shiro



# activeMQ

JMS 即 Java 消息服务（Java Message Service）应用程序接口，是一个Java平台中关于面向消息中间件（MOM）的 API，用于在两个应用程序之间，或分布式系统中发送消息，进行异步通信。
Java 消息服务是一个与具体平台无关的 API，绝大多数 MOM 提供商都对 JMS 提供支持。

JMS 只是接口，不同的提供商或者开源组织对其有不同的实现，ActiveMQ 就是其中之一，它支持JMS，是 Apache 推出的。JMS 中有几个对象模型：

```xml
连接工厂：ConnectionFactory
JMS连接：Connection
JMS会话：Session
JMS目的：Destination
JMS生产者：Producer
JMS消费者：Consumer
JMS消息两种类型：点对点 和 发布/订阅。
```

可以看出 JMS 实际上和 JDBC 有点类似，JDBC 是可以用来访问许多不同关系数据库的 API，而 JMS 则提供同样与厂商无关的访问方法，以访问消息收发服务。
本项目主要使用 ActiveMQ，其他像是rocketMQ，kafka等。

1. 生产者（Producer）：消息的发送者，负责创建消息，并将其发送到消息队列中。
2. 消费者（Consumer）：消息的接收者，负责从消息队列中获取消息。
3. 消息队列（Message Queue）：用来存储消息，**消息队列是 JMS 的核心**，生产者将消息发送到消息队列， 消费者从消息队列中获取消息。
4. 连接工厂（Connection Factory）：用来创建生产者与消费者与消息队列之间的连接。 
5. 连接（Connection）：生产者与消费者与消息队列之间的一个TCP连接。 
6. 会话（Session）：生产者与消费者与消息队列之间的一个连接，可以创建消息，发送消息，接收消息。 
7. 目的地（Destination）：消息队列的名称，或者队列或主题。 
8. 消息（Message）：消息队列中传输的数据，由消息头和消息体组成。 
9. 消息头（Message Header）：消息的属性，包括消息的优先级，消息的创建时间，消息的过期时间，消息的重复次数 
10. 消息体（Message Body）：消息的内容。 
11. 消息属性（Message Properties）：消息的属性，包括消息的优先级，消息的创建时间，消息的过期时间，消息的重复次数。 
12. 消息类型（Message Type）：消息的类型，包括队列和主题。 
13. 消息模式（Message Pattern）：消息的模式，包括点对点和发布/订阅。 
14. 消息驱动者（Message Driven）：消息驱动者，消息生产者，消息消费者。

   
## 安装

### docker 安装

```bash
docker pull rmohr/activemq
```

```shell
docker run -d --name activemq -p 61616:61616 -p 8161:8161 rmohr/activemq
```
将容器的61616端口（ActiveMQ的默认端口）和8161端口（ActiveMQ的管理界面端口）映射到你的主机的相应端口。

在浏览器中访问http://localhost:8161 来查看ActiveMQ的管理界面。

>http://localhost:8161/admin/

默认的用户名和密码都是admin。


### 生产者

MsgProducer.java

>com/example/lyc/springboot/demo/producer/MsgProducer.java

```java
@Service
public class MsgProducer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination destination, String msg) {
        jmsMessagingTemplate.convertAndSend(destination, msg);
    }
}
```

### 消费者

QueueConsumer.java

>com/example/lyc/springboot/demo/consumer/QueueConsumer.java

```java
@Slf4j
@Service
public class QueueConsumer {

    /**
     * 接收点对点消息
     * @param msg
     */
    @JmsListener(destination = ActiveMqConfig.QUEUE_NAME)//
    public void receiveQueueMsg(String msg) {
        log.info("消费者 收到的消息为：" + msg);
    }
}

```


### Controller

ActiveMqController.java

>com/example/lyc/springboot/demo/controller/ActiveMqController.java

```java

@Slf4j
@RestController
@RequestMapping("/activemq")
public class ActiveMqController {
    private static final Logger logger = LoggerFactory.getLogger(ActiveMqController.class);
    @Resource
    private MsgProducer producer;
    @Resource
    private Destination queue;

    @GetMapping("/send/queue")
    public String sendQueueMessage() {
        logger.info("===开始发送点对点消息===");
        producer.sendMessage(queue, "Queue: hello activemq!");
        log.info("===生产者 发送点对点消息===");
        return "success";
    }
}
```



<img width="1346" alt="image" src="https://github.com/liyinchigithub/springboot-learn/assets/19643260/1c40c36b-aca2-4d31-b42f-15f362b25bf3">



# WebSocket

* 1.WebSocketConfig.java
>com/example/lyc/springboot/demo/config/WebSocketConfig.java


* 2.MyWebSocketHandler.java
>com/example/lyc/springboot/demo/handler/MyWebSocketHandler.java


* 3.测试webSocket
>TestWebSocket.html



## webSocket跨域问题

如果你的前端应用和后端应用不在同一个域，可能会遇到跨域问题。
你需要在Spring Boot应用中配置跨域支持。
在registerWebSocketHandlers方法中，可以调用setAllowedOrigins方法来设置允许的跨域源。

## 状态码101 Switching Protocols 当我发起content时

状态码101 Switching Protocols是一个正常的响应，它表示服务器理解了客户端的请求，并且正在切换协议，这在WebSocket握手过程中是常见的。

当你的客户端发起一个WebSocket连接请求时，它会首先发送一个HTTP请求到服务器，这个请求的头部包含一个"Upgrade: websocket"，表示客户端希望升级协议到WebSocket。

如果服务器支持WebSocket，它会返回一个状态码101 Switching Protocols的响应，同时响应的头部也会包含"Upgrade: websocket"，表示服务器同意切换到WebSocket协议。

这个过程被称为WebSocket的握手过程。握手成功后，客户端和服务器之间的通信就会切换到WebSocket协议，可以进行全双工的通信。

所以，如果你在发起连接时看到状态码101，那么这是正常的，表示你的WebSocket连接正在被建立。


### webSocket调试输出

在调试过程中，可以使用日志输出来查看WebSocket的**握手过程**和**通信状态**。

你可以通过配置日志输出级别为DEBUG，来

MyWebSocketHandler.java

### websocket ***握手过程**和**通讯状态**

1. 握手过程：

- 客户端发送一个HTTP请求到服务器，请求头包含Upgrade: websocket和Connection: Upgrade，表示客户端希望升级协议到WebSocket。

- 如果服务器支持WebSocket，它会返回一个状态码为101 Switching Protocols的HTTP响应，响应头也包含Upgrade: websocket和Connection: Upgrade，表示服务器同意切换到WebSocket协议。

- 握手成功后，客户端和服务器之间的通信就会切换到WebSocket协议，可以进行全双工的通信。

2. 通讯状态：

- OPEN：连接已经开启并准备好进行通信。

- CLOSING：连接正在关闭的过程中，即已经收到了一个关闭帧，并正在等待对方的关闭帧。

- CLOSED：连接已经关闭或者无法打开。

- CONNECTING：连接还没有开启，握手还在继续。

在Spring Boot中，你可以使用WebSocketSession的isOpen方法来检查连接是否开启，使用close方法来关闭连接。在TextWebSocketHandler的afterConnectionEstablished和afterConnectionClosed方法中，你可以添加自定义的逻辑来处理连接开启和关闭的事件。



* 客户端（前端）

>TestWebSocket.html

（1）首次，客户端发送一个HTTP请求到服务器，请求头包含Upgrade: websocket和Connection: Upgrade，表示客户端希望升级协议到WebSocket。

<img width="700"  height="400" alt="image" src="https://github.com/liyinchigithub/springboot-learn/assets/19643260/28d09fe2-f7a5-464f-9254-fd34d642485c">

如果服务器支持WebSocket，它会返回一个状态码为101 Switching Protocols的HTTP响应，响应头也包含Upgrade: websocket和Connection: Upgrade，表示服务器同意切换到WebSocket协议。


（2） 握手成功后，客户端和服务器之间的通信就会切换到WebSocket协议，可以进行全双工的通信。

<img width="700"  height="400" alt="image" src="https://github.com/liyinchigithub/springboot-learn/assets/19643260/2afd0e06-18d8-4171-afcb-d3d64ba0658a">


（3）客户端（前端）发送和接收内容

<img width="700"  height="400" alt="image" src="https://github.com/liyinchigithub/springboot-learn/assets/19643260/e8b735ad-98ab-41d5-b6b5-7499b8507b3f">



* 服务端

**建立连接**

<img width="700" height="400" alt="image" src="https://github.com/liyinchigithub/springboot-learn/assets/19643260/9400f385-c07c-47af-a73a-0684f1361ed9">

**关闭连接**

<img width="700" height="400" alt="image" src="https://github.com/liyinchigithub/springboot-learn/assets/19643260/9400f385-c07c-47af-a73a-0684f1361ed9">







# 常见问题

1.端口占用

mac 查看进程占用
```bas
lsof -i:8088
kill -s 9 进程号
```


2. ActiveMQ如何使用**topic**和**consumerGroup**？
   在ActiveMQ中，**主题（Topic）**和**消费者组（Consumer Group）** 的概念与RocketMQ中的稍有不同。
   RocketMQ才有**topic**和**consumerGroup**，不同项目都订阅了主题，但如果在同一个消费组，则只有一个消费者能消费到消息。
   而ActiveMQ使用JMS（Java Message Service）规范，其中定义了[两种消息模型]：**点对点**（Point-to-Point）和**发布/订阅**（Publish/Subscribe）。

   * 主题（Topic）：
  在**发布/订阅**模型中，消息**生产者**（Publisher）将消息发布到**主题**（Topic），所有订阅了该主题的**消费者**（Subscriber）都会接收到这些消息，这与RocketMQ中的主题概念相似。

  * 消费者组（Consumer Group）：
  
  然而，ActiveMQ并没有消费者组（Consumer Group）的概念，在ActiveMQ中，每个消费者都是独立的，每个消费者都会接收到所有发布到它订阅的主题的消息。

  RocketMQ中的消费者组（Consumer Group）与ActiveMQ中的消费者（Consumer）的概念是类似的。

  在ActiveMQ中，消费者组（Consumer Group）与消费者（Consumer）的关系类似于RocketMQ中的主题（Topic）与订阅关系（Subscription）。

3. 在ActiveMQ中我有多个项目，是不是只要每个项目消费者，订阅了该主题都能够收到生产者发送的消息？

是的，你的理解是正确的。
在ActiveMQ中，主题（Topic）是实现发布-订阅模型的方式。
如果生产者发送消息到一个主题，那么所有订阅了这个主题的消费者都会接收到这个消息，无论这些消费者属于哪个项目。
这种模型非常适合广播类型的消息，例如，你想将一个消息发送给多个接收者。（**有点像安卓广播组件**）

**请注意，这与队列（Queue）模型是不同的**。
在队列模型中，每个消息只会被一个消费者接收。
这种模型适合点对点类型的消息。

4. 通常情况下，我们会在启动应用时自动启动消息监听，而不是通过Controller触发，那么要如何修改？

在Spring Boot应用中，你可以使用**@JmsListener**注解来自动注册消息监听器。
这个注解应该被添加到你希望用来处理消息的方法上。
当应用启动时，Spring会自动注册这些方法为消息监听器。

```java
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @JmsListener(destination = "queue.sample")
    public void receive(String message) {
        System.out.println("Received message: " + message);
    }
}
```

在这个示例中，receive方法被注解为一个消息监听器，它会监听名为queue.sample的队列。
当这个队列中有新的消息时，receive方法会被自动调用，参数message就是接收到的消息。
需要确保你的消费者类被Spring管理（例如，通过@Component注解），并且@JmsListener注解的方法有正确的签名。
具体来说，方法的参数应该与发送的消息类型相匹配。
例如，如果你发送的是文本消息，那么方法的参数应该是String。

如果你的消费者是通过Controller触发的，你可能需要将其改为上述方式，**以便在应用启动时自动启动消息监听**。



5.接口返回一个html页面

>src/main/resources/templates/TestPage.html

```java
package com.example.lyc.springboot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IoTController {

    @GetMapping("/page")
    public String getPage() {
        return "TestPage";
    }

}
```


6. 接口存储某个值，用于其他接口来获取这个isGo的值

用Spring的@SessionAttribute或者@SessionAttributes来在多个请求之间共享数据。


（1）首先，你需要在你的控制器类上添加@SessionAttributes注解，并指定你想要存储在session中的属性名称，

```java
@Controller
@SessionAttributes("isGo")
public class IoTController {
    // ...
}
```

（2）然后，你可以在你的doAction方法中将isGo参数的值存储到session中：
```java
@GetMapping("/action")
public String doAction(@RequestParam boolean isGo, Model model) {
    model.addAttribute("isGo", isGo);
    return "actionResult";
}

```

（3）最后，你可以在你的新接口中获取session中的isGo值：
```java
@GetMapping("/getIsGo")
public String getIsGo(Model model) {
        Boolean isGo = (Boolean) model.getAttribute("isGo");
        // 在这里处理isGo值
        return "getIsGoResult";
        }
```


（4）使用JsonResult类来封装你的接口返回结果。

在IoTController类中，你需要将String类型的返回值改为JsonResult类型的返回值。
然后，你可以使用JsonResult的构造函数来创建返回结果。
这是修改后的IoTController类：

```java
import com.example.lyc.springboot.demo.commons.api.JsonResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/v1")
@SessionAttributes("isGo")
public class IoTController {

    @GetMapping("/page")
    public String getPage() {
        return "TestPage";
    }

    @GetMapping("/action")
    public JsonResult<String> doAction(@RequestParam boolean isGo, Model model) {
        model.addAttribute("isGo", isGo);
        return new JsonResult<>("actionResult");
    }

    @GetMapping("/getIsGo")
    public JsonResult<Boolean> getIsGo(Model model) {
        Boolean isGo = (Boolean) model.getAttribute("isGo");
        return new JsonResult<>(isGo);
    }
}
```


