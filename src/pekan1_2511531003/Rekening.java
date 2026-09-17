package pekan1_2511531003;
import java.text.NumberFormat;
import java.util.Locale;
import pekan2_2511531003.Transaksi;

import java.util.ArrayList;

public class Rekening {
   String nomorRekening;
   String namaPemilik;
   double saldo;
   
   //Implementasi Asosiasi (1-to-many)
   ArrayList<Transaksi> riwayatTransaksi;
   
   NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
   
public Rekening(String nomor,String nama,double saldoAwal) {
	this.nomorRekening = nomor;
	this.namaPemilik = nama;
	this.saldo = saldoAwal;
	//wajib menginisialisasi ArrayList di dalam costructor agar tidak NullPointerException
	this.riwayatTransaksi = new ArrayList<>();
	
	System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp" + saldo);
}

public String getNoRekening() {
	return nomorRekening;
}

public void setorTunai(double nominal) {
	if (nominal <= 500000) {
		saldo += nominal;
		//Merekam riwayat (Pembuatan objek Transaksi di dalam method)
		String idTrx = "TRX-S-" + System.currentTimeMillis();
		Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
		riwayatTransaksi.add(trxBaru);
		
		System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
	} else {
		System.out.println("Gagal: Nominal setor tidak boleh lebih dari 500000!");
		NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
	}
}

public void tarikTunai(double nominal) {
	if (nominal < 10000) {
		System.out.println("Transaksi Gagal: Minimal Nominal penarikan 10000!");
	} else if (nominal > saldo) {
		System.out.println("Transaksi Gagal:Saldo tidak mencukupi. Saldo Anda: Rp" + nominal);
	} else {
		saldo -= nominal;
		//Merekam riwayat (Pembuatan objek Transaksi di dalam method)
				String idTrx = "TRX-T-" + System.currentTimeMillis();
				Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
				riwayatTransaksi.add(trxBaru);
		System.out.println ("Tarik tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
	}
}

public void cekInformasi() {
	System.out.println("--- INFO REKENING ---");
	System.out.println("No. Rekening : " + nomorRekening);
	System.out.println("Nama Pemilik : " +namaPemilik);
	NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
	System.out.println("Saldo akhir : " + rupiah.format(saldo));
	System.out.println("----------------");
  }

public void cetakMutasi() {
	if (riwayatTransaksi.isEmpty()) {
		System.out.println("Belum ada transaksi pada rekening ini");
	} else {
		for (Transaksi transaksi : riwayatTransaksi) {
			transaksi.cetakDetail();
		}
	}
}
public void transaksiTerbaru() {
	if (riwayatTransaksi.isEmpty()) {
		System.out.println("Belum ada transaksi pada rekening ini");
	} else {
		System.out.println("3 Transaksi Terbaru");
		int mulai = Math.max(0, riwayatTransaksi.size() - 3);
		for (int i = riwayatTransaksi.size() - 1; i >= mulai; i-- ) {
			riwayatTransaksi.get(i).cetakDetail();
		}
	}
		
	}
}


