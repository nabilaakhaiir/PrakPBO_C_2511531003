package pekan3_2511531003;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner input = new Scanner(System.in);
    	ArrayList<Rekening> daftarRekening = new ArrayList<>();
    	Rekening akunAktif = null; //objek belum diinisialisasi (null)
    	boolean isRunning = true;
    	
    	
    	System.out.println("=== SISTEM PERBANKAN MINI ===");
    	
    	while (isRunning) {
    		System.out.println("\nMenu Utama");
    		System.out.println("1. Buka Rekening Baru");
    		System.out.println("2. Setor Tunai");
    		System.out.println("3. Tarik Tunai");
    		System.out.println("4. Cek Informasi Rekening");
    		System.out.println("5. Ganti Akun");
    		System.out.println("6. Cetak Mutasi(Riwayat)");
    		System.out.println("7. Transaksi Terbaru");
    		System.out.println("8. Ganti Pin:");
    		System.out.println("0. Keluar");
    		System.out.print("Pilih menu:");
    		
    		int pilihan = input.nextInt();
    		input.nextLine();  //membersihkan buffer enter
    		
    		switch(pilihan) {
    		case 1:
    			System.out.print("Masukkan No Rekening: ");
    			String no = input.nextLine();
    			System.out.print("Masukkan Nama Pemilik: ");
    			String nama = input.nextLine();
    			System.out.print("Masukkan Saldo Awal: ");
    			double saldo = input.nextDouble();
    			System.out.print("Masukkan PIN: ");
    			String pin = input.nextLine();
    			
    			
    			input.nextLine();
    			Rekening rekeningBaru = new Rekening(no, nama, saldo, pin);
    		    daftarRekening.add(rekeningBaru);
    		    akunAktif = rekeningBaru;
    		    
    		    System.out.println("Akun tersebut sekarang menjadi akun aktif.");
    			break; 
    			
    		case 2:
    			if(akunAktif == null) {
    				System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
    			}else {
    				System.out.print("Masukkan nominal setor: ");
    				double setor = input.nextDouble();
    				akunAktif.setorTunai(setor); //Memanggil behavior / method
    				
    			}
    			break;
    			
    		case 3:
    			if (akunAktif == null) {
    				System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
    			}else  {
    				System.out.print("Masukkan PIN: ");
    				String Pin = input.nextLine(); 
    				
    			if (akunAktif.otentikasi(Pin)) {	
    			   System.out.print("Masukkan nominal tarik: ");
				   double tarik = input.nextDouble();
				   akunAktif.tarikTunai(tarik);
    		    	   
				   } else {
					   System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
				   }
    			}
				 break;  
    		
				   
    		case 4:
    			if (akunAktif == null) {
    				System.out.println ("Error: Anda belum membuka rekening!");
    			} else {
    				akunAktif.cekInformasi();
    			}
    			break;
    			
    		case 5:
    			if (daftarRekening.isEmpty()) {
    				System.out.println("Belum ada rekening yang tersedia.");		
    			} else {
    				System.out.println("\nDaftar Rekening: ");
    				for (int i = 0; i < daftarRekening.size(); i ++) {
    					System.out.println((i + 1) + ". Rekening ke-" + (i + 1));
    				}
    			}
    			System.out.print("Masukkan Nomor Rekening yang ingin digunakan: ");
    			String nomorCari = input.nextLine();
    			boolean ditemukan = false;
    			for (Rekening rekening : daftarRekening) {
    				if (rekening.getNoRekening().equals(nomorCari)) {
    					akunAktif = rekening;
    					ditemukan = true;
    					System.out.println("Berhasil mengganti akun aktif");
    					break;
    				}
    			}
    			if (! ditemukan) {
    				System.out.println("Rekening tidak ditemukan");
    			}
    			break;
    			
    		case 6:
    			if (akunAktif == null) {
    				System.out.println ( "Belum ada transaksi pada rekening ini");
    			} else {
    				System.out.print("Masukkan PIN: ");
    				String Pin = input.nextLine(); 	
    			if (akunAktif.otentikasi(Pin)) {
    				akunAktif.cetakMutasi();
				   } else {
					   System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
				   }
    			}
    			break;
    			
    		case 7:
    			if (akunAktif == null) {
    				System.out.println ( "Belum ada transaksi pada rekening ini");
    			} else {
    				akunAktif.transaksiTerbaru();
    			}
    			break;
    			
    		case 8:
    		    if (akunAktif == null) {
    		        System.out.println(
    		            "Error: Anda belum memiliki rekening!"
    		        );
    		    } else {
    		        System.out.print("Masukkan PIN lama: ");
    		        String pinLama = input.nextLine();
    		        if (!akunAktif.otentikasi(pinLama)) {
    		            System.out.println(
    		                "Akses Ditolak: PIN lama yang Anda masukkan salah!"
    		            );
    		        } else {
    		            System.out.print("Masukkan PIN baru: ");
    		            String pinBaru = input.nextLine();
    		            if (pinLama.equals(pinBaru)) {
    		                System.out.println(
    		                    "Gagal: PIN baru tidak boleh sama dengan PIN sebelumnya!"
    		                );
    		            } else {
    		                System.out.print("Konfirmasi PIN baru: ");
    		                String konfirmasiPin = input.nextLine();
    		                if (!pinBaru.equals(konfirmasiPin)) {
    		                    System.out.println(
    		                        "Gagal: Konfirmasi PIN baru tidak sesuai!"
    		                    );
    		                } else {
    		                    boolean berhasil =
    		                        akunAktif.gantiPin(pinLama, pinBaru);
    		                    if (berhasil) {
    		                        System.out.println(
    		                            "PIN berhasil diganti!"
    		                        );
    		                    } else {
    		                        System.out.println(
    		                            "Gagal mengganti PIN."
    		                        );
    		                    }}}}}
    		    break;
    			
    		case 0:
    			isRunning = false;
    			System.out.println("Sistem ditutup. Terima Kasih!");
    			break;
    			
    			default:
    				System.out.println ("Pilihan tidak valid!");
    		}
    	}
    	input.close();   	
    }
}
