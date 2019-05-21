# 消费端限流
RabbitMQ提供了一种`qos`（服务质量保证）功能，即在非自动确认消息的前提下，如果一定数据的消息（通过基于`consume`或者`channel`设置Qos的值）未被确认前，不进行消费新的消息。**不能设置为自动签收！**

```java
/**
 * Request specific "quality of service" settings.
 *
 * These settings impose limits on the amount of data the server
 * will deliver to consumers before requiring acknowledgements.
 * Thus they provide a means of consumer-initiated flow control.
 * @see com.rabbitmq.client.AMQP.Basic.Qos
 * @param prefetchSize maximum amount of content (measured in
 * octets) that the server will deliver, 0 if unlimited
 * 
 * @param prefetchCount maximum number of messages that the server
 * will deliver, 0 if unlimited
 * @param global true if the settings should be applied to the
 * entire channel rather than each consumer
 * @throws java.io.IOException if an error is encountered
 * prefetchSize 消息的大小，一般为0，不做限制
 * prefetchCount：会告诉RabbitMQ不要同时给一个消费者推送多个N个消息，
 * 即一个有N个消息还没有ack，则该consumer将block掉，知道有消息ack 
 * global true、false 是否设置应用于channel，限制在channel级别还是consumer级别
 */
void basicQos(int prefetchSize, int prefetchCount, boolean global) throws IOException;
```

- `prefetchSize`和global 这两项，rabbitmq没有实现，暂且不研究
- `prefetchCount` 在no_ack= false 的情况下不生效，即在自动应答的情况下这个值是不生效的

# 消费端ACK与重回队列

## 消费端的手动ack和nack

- 消费端进行消费的时候，如果由于业务异常我们可以进行日志的记录，然后进行补偿！
- 如果由于服务器宕机严重问题，那我们就需要手工进行ack保障消费端消费成功！



## 消费端的重回队列

- 消费端重回队列是为了对没有处理成功的消息，把消息重新回递给`Broker`！

- 一般我们在实际应用中，都会关闭重回队列，也就是设置为`false`



# TTL 队列

TTL是`Time  To Live` 的缩写，也就是生存时间。

- `RabbitMQ` 支持消息的过期时间，在消息发送时可进行指定
- `RabbitMQ `支持队列的过期时间，从消息入队列开始计算，只要超过了队列的超时时间配置，那么消息会自动的清除

# 死信队列

DLX：`Dead-Letter_Exchange`

- 利用`DLX`，当消息在一个队列中变成死信之后，他能被重新publish到另一个Exchange，这个Exchange就是`DLX`



## 几种情况：

- 消息被拒绝（`basic.reject` / `basic.nack`）并且 `requeue = false`
- 消息`TTL`过期
- 队列达到最大长度

`DLX`是一个正常的Exchange，和一般的Exchange没有区别，它能在任何对鞋上被指定，实际上就是设置某个队列的属性

当这个队列中有死信时，`RabbitMQ`就会自动的讲这个消息重新发布到设置的Exchange上去，进而被路由到另一个队列。

可以监听这个队列中消息做相应的处理，这个特性可以弥补`RabbitMQ 3.0`以前支持的`immeditae`参数的功能



# RabbitMQ 整合Spring AMQP

- Rabbit Admin
- Spring AMQP声明
- RabbitTemplate
- SimpleMessageListenerContainer
- MessageListenerAdapter
- MessageConverter



## Rabbit Admin

可以很好的操作RabbitMQ，在Spring中直接进行注入即可

```java
@Bean
public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
    RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
    rabbitAdmin.setAutoStartup(true);
    return rabbitAdmin;
}
```

- `autoStartup`必须设置为`true`，否则Spring容器不会加载RabbitAdmin类
- `RabbitAdmin`底层实现就是从`Spring`容器中获取`Exchange`、`Binding`、`RoutingKey`以及`Queue`的`@Bean`声明
- 然后使用`RabbitTemplate`的`execute`的方法执行对应的声明、修改、删除等一些类基础功能操作



## RabbitTemplate 消息模板

- 与Spring AMQP整合的时候进行发送消息的关键类
- 该类提供了丰富的发送消息方法，包括可靠投递消息的方法，回调监听消息接口`ConfirmCallback`、返回值确认接口`ReturnCallback`等等。同样需要注入到Spring容器中，然后使用



## SimpleMessageListenerContainer

监听多个队列，自动启动，自动声明

- 设置事务特性、事务管理器、事务属性、事务容量（并发）、是否开启事务、回滚消息等
- 设置消费者数量、最小最大数据、批量消费
- 设置消息确认和自动确认模式、是否重回队列、异常捕获`handler`函数
- 设置消费者标签生成策略、是否独占模式、消费者属性等
- 设置具体的监听器、消息转换器等



## MessageListenerAdapter 消息监听适配器

- `defaultListenerMethod` 默认监听方法名称：用于设置监听方法名称
- `Delegate`委托对象：实际真实的委托对象，用于处理消息
- `queueOrTagToMethodName`队列标识与方法名称组合的集合
- 可以一一进行队列与方法名称的匹配
- 队列和方法名称绑定，即指定队列里的消息会被绑定的方法所接受处理

## MessageConverter 消息转换器

正常情况下消息体为二进制的数据方法进行传输，入股希望内部帮我们进行转换，或者指定自定义的额转化器，就需要用到转换器



# SpringBoot 整合配置详解

- publisher-confirms 实现一个监听器用于监听Broker端给我们返回的确认请求：

`RabbitTemplate.ConfirmCallback`

- publisher-returns 保证消息对Broker端是可达的，如果出现路由键不可达的情况，则使用监听器对不可达的消息进行后续处理，保证消息的路由成功

`RabbitTemplate.ReturnCallback`



- 在发送消息的时候对template进行配置`mandatory=true`保证监听有效
- 生产端还可以配置其他属性，如果发送重试，超时时间，次数，间隔等

