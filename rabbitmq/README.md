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
 * prefetchCount：会告诉RabbitMQ不要同时给一个消费者推送多个N个消息，即一个有N个消息还没有ack，则		* 该consumer将block掉，知道有消息ack 
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