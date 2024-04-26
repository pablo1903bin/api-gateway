package comapigateway.requests;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "compra-service", path = "api/compra",
		// url="${compras.service.url}",
		configuration = FeignConfiguration.class)
public interface CompraServiceRequest {

	@PostMapping
	Object saveCompra(@RequestBody Object requestBody);

	@GetMapping("{userId}")
	List<Object> getAllComprasOfUser(@PathVariable("userId") Long userId);

}
