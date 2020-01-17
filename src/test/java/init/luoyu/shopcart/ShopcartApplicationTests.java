package init.luoyu.shopcart;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShopcartApplicationTests {

    @Test
    void contextLoads() {

        //创建客户端
        RedisClient client = RedisClient.create("redis://192.168.1.195");
        //建立连接
        StatefulRedisConnection<String, String> connect = client.connect();
        //获取会话
        RedisCommands<String, String> sync = connect.sync();
        //指定密码
        sync.auth("123456");
        //设置值
        sync.set("test","测试值");
        //获取值
        String data = sync.get("test");

        System.out.println(data);

        }

}
