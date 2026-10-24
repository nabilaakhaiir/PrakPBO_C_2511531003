package pekan5_2511531003;
import java.text.NumberFormat;
import java.util.Locale;

import java.util.ArrayList;

public class Rekening {
   private String nomorRekening;
   private String namaPemilik;
   private String pin; //data sensitif 
   
   //Gunakan protected agar Subclass bisa mengaksesnya langsung
   protected double saldo;
   protected ArrayList<Transaksi> riwayatTransaksi;
   
   NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
   
public Rekening(String nomor,String nama,double saldoAwal, String pinAwal) {
	this.nomorRekening = nomor;
	this.namaPemilik = nama;
	this.saldo = saldoAwal;
	
	//Validaso PIN di dalam Constructor
	if (pinAwal.length() == 6) {
		this.pin = pinAwal;		
	} else {
		System.out.println("Peringatan: PIN harus 6 digit! Menggunakan PIN default 123456");
		this.pin = "123456";
	}
	
	this.riwayatTransaksi = new ArrayList<>();
	System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp" + saldo);
}

//3. Getter untuk atribut yang diizinkan dibaca publik
public String getNoRekening() { return nomorRekening;}
public String getNamaPemilik() { return namaPemilik;}

//4.Method Otentikasi Internal (Validasi enkapsulasi)
public boolean otentikasi(String inputPin) {
	return this.pin.equals(inputPin);
	}
	public boolean gantiPin(String pinLama, String pinBaru) {
	    if (!otentikasi(pinLama)) {
	        return false;
	    }
	    if (pinLama.equals(pinBaru)) {
	        return false;
	    }
	    pin = pinBaru;
	    return true;
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

public boolean tarikTunai(double nominal) {
	if (nominal <= 0) {
		System.out.println("Gagal: Nominal tarik harus lebih dari 0!");
		return false;
	} else if (nominal > saldo) {
		System.out.println("Transaksi Gagal:Saldo tidak mencukupi. Saldo Anda: Rp" + saldo);
		return false;
	} else {
		saldo -= nominal;
		//Merekam riwayat (Pembuatan objek Transaksi di dalam method)
				String idTrx = "TRX-T-" + System.currentTimeMillis();
				riwayatTransaksi.add(new Transaksi(idTrx, "Debit", nominal));
				
		System.out.println ("Tarik tunai Rp" + nominal + " berhasil. Sisa saldo: Rp" + saldo);
		return true;
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
		}}}

public void transfer(Rekening tujuan, double nominal, String pinInput) {
	if (!otentikasi(pinInput)) {
		System.out.println("Transfer Gagal: PIN yang Anda masukkan salah!");
		return; 
	}
	if (this == tujuan) {
		System.out.println("Transfer Gagal: Tidak dapat melakukan transfer ke rekening sendiri!");
		return;
		}

	// 3. Panggil method tarikTunai pada pengirim (menggunakan overriding jika Giro)
	boolean penarikanBerhasil = this.tarikTunai(nominal);

	// 4. Jika penarikan berhasil, lanjutkan dengan menyetor ke rekening tujuan
	if (penarikanBerhasil) {
		 tujuan.setorTunai(nominal);
		 System.out.println("Transfer sebesar Rp" + nominal + " ke rekening " 
		 + tujuan.getNoRekening() + " (" + tujuan.getNamaPemilik() + ") BERHASIL!");
	} else {
		 System.out.println("Transfer Gagal: Saldo tidak mencukupi atau nominal penarikan tidak valid.");
	 }
	}	
}





