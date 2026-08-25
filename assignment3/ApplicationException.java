// Base Exception
class ApplicationException extends Exception { public ApplicationException(String m) { super(m); } }

// Sub-Categories
class ProductException extends ApplicationException { public ProductException(String m) { super(m); } }
class PaymentException extends ApplicationException { public PaymentException(String m) { super(m); } }
class OrderException extends ApplicationException { public OrderException(String m) { super(m); } }

// Leaf Exceptions
class ProductNotFoundException extends ProductException { public ProductNotFoundException(String m) { super(m); } }
class OutOfStockException extends ProductException { public OutOfStockException(String m) { super(m); } }
class InvalidPaymentException extends PaymentException { public InvalidPaymentException(String m) { super(m); } }
class InsufficientFundsException extends PaymentException { public InsufficientFundsException(String m) { super(m); } }
class EmptyCartException extends OrderException { public EmptyCartException(String m) { super(m); } }