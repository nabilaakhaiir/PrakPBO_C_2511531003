package shop.payment;

import shop.exception.PaymentFailedException; // sesuaikan dengan package exception-mu

public class QrisPayment implements PaymentMethod {

    private static final long BATAS_MAKSIMAL = 10_000_000L;

    @Override
    public String name() {
        return "QRIS";
    }

    @Override
    public void pay(long amount) throws PaymentFailedException {
        if (amount > BATAS_MAKSIMAL) {
            throw new PaymentFailedException("transaksi QRIS maksimal Rp10.000.000");
        }
        // jika lolos, pembayaran dianggap berhasil
    }
}