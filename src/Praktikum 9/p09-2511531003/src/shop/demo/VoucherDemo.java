package shop.demo;

import shop.model.Voucher;

public class VoucherDemo {
    public static void main(String[] args) {
        Voucher voucherSah = new Voucher.Builder("KOPI20", 20)
                .minimumPurchase(50_000)
                .description("Diskon 20% untuk pembelian kopi")
                .build();

        System.out.println("Voucher sah dibuat: " + voucherSah.getCode()
                + " (" + voucherSah.getPercent() + "%), min belanja: "
                + voucherSah.getMinimumPurchase() + ", ket: " + voucherSah.getDescription());

        try {
            Voucher voucherTidakSah = new Voucher.Builder("DISKON75", 75)
                    .build();
            System.out.println("Voucher tidak sah dibuat: " + voucherTidakSah.getCode());
        } catch (IllegalStateException e) {
            System.out.println("Voucher tidak sah ditolak -> " + e.getMessage());
        }
    }
}