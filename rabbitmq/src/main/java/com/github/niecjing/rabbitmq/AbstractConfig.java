package com.github.niecjing.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Nathan
 */
public class AbstractConfig {

    public ConnectionFactory getConnectionFactory() {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("10.166.15.52");
        connectionFactory.setPort(5671);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin123456");
        return connectionFactory;
    }


}
