package shop.testdouble;

import shop.payment.PaymentMethod;

public class FakePaymentMethod implements PaymentMethod {

    private long lastAmount;
    private int callCount;

    public FakePaymentMethod() {
        this.lastAmount = 0;
        this.callCount = 0;
    }

    @Override
    public String name() {
        return "Fake";
    }

    @Override
    public void pay(long amount) {
        this.lastAmount = amount;
        callCount++;
    }

    public long getLastAmount() {
        return lastAmount;
    }

    public int getCallCount() {
        return callCount;
    }
}