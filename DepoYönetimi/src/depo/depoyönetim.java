package depo;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class depoyönetim {

	 static Scanner scan = new Scanner(System.in);
	    public static Map<Integer, urunTanimlama> urunListesiMap = new HashMap<Integer, urunTanimlama>();
	    public static int urunId = 1000;

	    public static void girisPaneli() {
	        System.out.println("====================================\nDEPO YONETIM PANELI\n" +
	                "====================================\n"
	                + "1- BULUNDURULACAK URUN TANIMLAMA\n2- URUN LÝSTELE\n3- DEPOYA URUN GÝRÝÞÝ\n4- URUNU RAFA KOY\n5- DEPODAN URUN ÇIKIÞI\n6- DEPO SÝSTEMÝNDEN ÇIKIÞ");
	        System.out.print("lütfen iþlem seciniz : ");
	        String secim = scan.next().toUpperCase(Locale.ROOT);
	        switch (secim) {
	            case "1":
	                urunTanimla();
	                girisPaneli();
	                break;
	            case "2"://Jasmina, zeynep, merve
	                urunListele();
	                girisPaneli();
	                break;
	            case "3":// oðuzhan, fatih
	                urunGirisi();
	                girisPaneli();
	                break;
	            case "4"://gökhan, hüseyin
	                urunuRafaKoy();
	                girisPaneli();
	                break;
	            case "5":// defne, þule
	                urunCikisi();
	                girisPaneli();
	                break;
	            case "6":
	                cikis();
	                break;
	            default:
	                System.out.println("hatalý giriþ yaptýnýz, tekrar deneyiniz. ");
	                girisPaneli();
	                break;
	        }
	    }


	    private static void urunTanimla() {
	       //urunTanimlama 	==>  urunun ismi, ureticisi ve birimi girilecek. id  alýnacak.

	        System.out.println("   ********* urun tanýmlama sayfasý *********");
	        System.out.println("ürün ismi giriniz: ");
	        scan.nextLine();//dummy
	        String urunIsmi = scan.nextLine();

	        System.out.println("üreticisini giriniz: ");
	        String uretici = scan.nextLine();

	        System.out.println("birimi giriniz: ");
	        String birim = scan.next();

	        int urunMiktar = 0;
	        String raf = " - ";

	        urunTanimlama urun = new urunTanimlama(urunIsmi, uretici, birim, urunMiktar, raf);//urun objesi oluþturuldu
	        urunListesiMap.put(urunId, urun); //map içerisine urunId key, urun objesi eklendi

	        urunId++; //her ürün giriþinde id bir arttýrýldý

	    }

	    private static void urunListele() {
	//urunListele 	==> tanimlanan urunler listelenecek. urunun adeti ve raf numarasi tanimlama yapilmadiysa default deger gorunsun.
	        Set<Map.Entry<Integer, urunTanimlama>> urunEntrySeti = urunListesiMap.entrySet();
	        System.out.println("---------------------------ÜRÜN LÝSTESÝ---------------------------------");
	        System.out.println("id       ismi         ureticisi       birim       miktar         raf" +
	                "\n----------------------------------------------------------------------");
	        for (Map.Entry<Integer, urunTanimlama> e : urunEntrySeti
	        ) {
	            Integer entryKey = e.getKey();
	            System.out.printf("%-8d %-12s %-15s %-12s %-12d %-9s\n", entryKey, urunListesiMap.get(entryKey).getUrunIsmi(), urunListesiMap.get(entryKey).getUretici(), urunListesiMap.get(entryKey).getBirim(), urunListesiMap.get(entryKey).getUrunMiktar(), urunListesiMap.get(entryKey).getRaf());
	        }
	    }

	    private static void urunGirisi() {
	        //urunGirisi 		==> giris yapmak istedigimiz urnunun id numarasi ile girecegiz.
	        System.out.println("   ********* urun giriþ sayfasý ********* ");
	        System.out.println("güncellemek istediðiniz ürün id si giriniz:");
	        int arananId = scan.nextInt();
	        if (urunListesiMap.keySet().contains(arananId)) {
	            System.out.println("miktar giriniz");
	            // integer data türü dýþýnda giriþ yapýlýnca exception yerine döngüye girecek ve geçerli deðeri yazdýrmasýný isteyecek
	            int guncelmiktar = 0;
	            boolean flag = true;
	            do {
	                try {
	                    if (flag == true) {
	                        scan.nextLine();
	                    }
	                    guncelmiktar = scan.nextInt();
	                    scan.nextLine();//dummy
	                    flag = false;
	                } catch (Exception e) {
	                    System.out.println("lütfen geçerli miktar giris yapýnýz");
	                }
	            } while (flag);

	            urunListesiMap.get(arananId).setUrunMiktar(guncelmiktar + urunListesiMap.get(arananId).getUrunMiktar());
	            //aranan id mize ait ürün map den getirildi ve eski ürün miktarýna eklendi
	            System.out.println("urun miktarýnýz güncel hale getirildi\n güncel miktar: " + urunListesiMap.get(arananId).getUrunMiktar());
	        } else {
	            System.out.println("aradýðýnýz ürün yoktur");
	        }
	    }

	    private static void urunuRafaKoy() {
	        //urunuRafaKoy 	==> listeden urunu sececegiz ve id numarasina gore urunu rafa koyacagiz.
	        System.out.println("   ********* urunu rafa ekleme sayfasý ********* ");
	        System.out.print("Rafa yerlestirmek istediginiz urunun ID sini giriniz : ");
	        int arananId = scan.nextInt();
	        if (urunListesiMap.keySet().contains(arananId)) {
	            System.out.println("hangi rafa kaldýracaðýnýzý yazýnýz:");
	            String guncelraf = scan.next();

	            urunListesiMap.get(arananId).setRaf(guncelraf);//eski raf deðerini ezip güncel raf deðerini girecek

	        } else {
	            System.out.println("bu ürün depoda mevcut deðildir malesef ");
	            urunuRafaKoy();
	        }

	    }

	    private static void urunCikisi() {
	        //urunCikisi 		==> listeden urunu sececegiz ve urunun cikis yapcagiz. burada urun listesinden sadece miktarda degisiklik yapilacak.
	        //	 						urun adedi 0dan az olamaz. 0 olunca urun tanimlamasi silinmesin. sadece miktari 0 olsun.
	        System.out.println("   ********* urun çýkýþ sayfasý ********* ");
	        System.out.print("Cýkýsýný yapmak  istediginiz urunun ID sini giriniz : ");
	        int arananId = scan.nextInt();
	        if (urunListesiMap.keySet().contains(arananId)) {
	            System.out.println("miktar giriniz");
	            int guncelmiktar = 0;
	            boolean flag = true;
	            do {
	                try {
	                    if (flag == true) {
	                        scan.nextLine();
	                    }
	                    guncelmiktar = scan.nextInt();
	                    scan.nextLine();//dummy
	                    flag = false;
	                } catch (Exception e) {
	                    System.out.println("lütfen geçerli bir tamsayý giriniz");
	                }
	            } while (flag);

	            if ( urunListesiMap.get(arananId).getUrunMiktar() - guncelmiktar < 0) {
	                System.out.println("deponuzda bu miktarda ürün yoktur.\n bulunan miktar: " + urunListesiMap.get(arananId).getUrunMiktar());
	            } else {
	                urunListesiMap.get(arananId).setUrunMiktar( urunListesiMap.get(arananId).getUrunMiktar() - guncelmiktar);
	                System.out.println("urun miktarýnýz güncel hale getirildi\n güncel miktar: " + urunListesiMap.get(arananId).getUrunMiktar());
	            }

	        } else {
	            System.out.println("aradýðýnýz ürün yoktur");
	        }
	    }


	    private static void cikis() {
	        System.out.println(" depo dan çýkýþ yaptýnýz. tekrar bekleriz..");
	    }

	}

