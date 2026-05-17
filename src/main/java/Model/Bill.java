package Model;

public record Bill(
        int id,
        String name_client,
        String name_product,
        int quantity,
        double price_product,
        double total_price
) {
}
