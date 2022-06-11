
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload


class Test {
    @RabbitListener(queues = ["\${queue.name}"])
    fun receive(@Payload fileBody: String) {
        println("Message $fileBody")
    }
}