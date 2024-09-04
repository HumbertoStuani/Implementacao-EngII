package sapc.sapcbackend.pagamentos;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;

import java.math.BigDecimal;

public class ConfigMercadoPago {

    static {
        // Inicialização do AccessToken
        MercadoPagoConfig.setAccessToken("TEST-20548466868034-060613-77109674151f80ec4c07d02f7b578a6b-242246025");
    }

    public static void main(String[] args) {
        PaymentClient client = new PaymentClient();

        PaymentCreateRequest createRequest =
                PaymentCreateRequest.builder()
                        .transactionAmount(new BigDecimal(1000))
                        .token("your_cardtoken")
                        .description("description")
                        .installments(1)
                        .paymentMethodId("visa")
                        .payer(PaymentPayerRequest.builder().email("dummy_email").build())
                        .build();

        try {
            Payment payment = client.create(createRequest);
            System.out.println(payment);
        } catch (MPApiException ex) {
            System.out.printf(
                    "MercadoPago Error. Status: %s, Content: %s%n",
                    ex.getApiResponse().getStatusCode(), ex.getApiResponse().getContent());
        } catch (MPException ex) {
            ex.printStackTrace();
        }
    }
}
