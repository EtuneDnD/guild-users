import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "dummy", url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=")
interface FireBaseAuthConsumer {
    @PostMapping
    fun getToken(): String
    //fun getToken(@RequestBody getTokenDto: GetTokenDto): String
}