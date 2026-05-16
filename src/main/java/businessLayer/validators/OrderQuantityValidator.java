package businessLayer.validators;

import Model.Orders;

public class OrderQuantityValidator implements Validator<Orders> {
        public void validate(Orders o) {
            if(o.getQuantity()<=0)
                throw new IllegalArgumentException("Order quantity cannot be negative");
        }
}
