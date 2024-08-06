## Lombok
### 实践中产出疑问
- 根据实践可以发现，在 Lombok 中只要添加对应说明就可以使用对应的方法，这是为什么呢？
### 寻找问题的答案
- ![img.png](img.png)
- - 通过打开 .class 文件我们可以知道，在添加对应标签后 Lombok会帮我们添加对应的方法
## record
- ![img_1.png](img_1.png)
- - 官方提出的新修饰词，会在编译阶段自动帮我们补齐

## maven
- - 创建一个测试代码
- ![img_3.png](img_3.png)
- - 快捷打包成 jar 包
- ![img_2.png](img_2.png)

