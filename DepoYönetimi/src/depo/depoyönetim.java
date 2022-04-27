package depo;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class depoy�netim {

	 static Scanner scan = new Scanner(System.in);
	    public static Map<Integer, urunTanimlama> urunListesiMap = new HashMap<Integer, urunTanimlama>();
	    public static int urunId = 1000;

	    public static void girisPaneli() {
	        System.out.println("====================================\nDEPO YONETIM PANELI\n" +
	                "====================================\n"
	                + "1- BULUNDURULACAK URUN TANIMLAMA\n2- URUN L�STELE\n3- DEPOYA URUN G�R���\n4- URUNU RAFA KOY\n5- DEPODAN URUN �IKI�I\n6- DEPO S�STEM�NDEN �IKI�");
	        System.out.print("l�tfen i�lem seciniz : ");
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
	            case "3":// o�uzhan, fatih
	                urunGirisi();
	                girisPaneli();
	                break;
	            case "4"://g�khan, h�seyin
	                urunuRafaKoy();
	                girisPaneli();
	                break;
	            case "5":// defne, �ule
	                urunCikisi();
	                girisPaneli();
	                break;
	            case "6":
	                cikis();
	                break;
	            default:
	                System.out.println("hatal� giri� yapt�n�z, tekrar deneyiniz. ");
	                girisPaneli();
	                break;
	        }
	    }


	    private static void urunTanimla() {
	       //urunTanimlama 	==>  urunun ismi, ureticisi ve birimi girilecek. id  al�nacak.

	        System.out.println("   ********* urun tan�mlama sayfas� *********");
	        System.out.println("�r�n ismi giriniz: ");
	        scan.nextLine();//dummy
	        String urunIsmi = scan.nextLine();

	        System.out.println("�reticisini giriniz: ");
	        String uretici = scan.nextLine();

	        System.out.println("birimi giriniz: ");
	        String birim = scan.next();

	        int urunMiktar = 0;
	        String raf = " - ";

	        urunTanimlama urun = new urunTanimlama(urunIsmi, uretici, birim, urunMiktar, raf);//urun objesi olu�turuldu
	        urunListesiMap.put(urunId, urun); //map i�erisine urunId key, urun objesi eklendi

	        urunId++; //her �r�n giri�inde id bir artt�r�ld�

	    }

	    private static void urunListele() {
	//urunListele 	==> tanimlanan urunler listelenecek. urunun adeti ve raf numarasi tanimlama yapilmadiysa default deger gorunsun.
	        Set<Map.Entry<Integer, urunTanimlama>> urunEntrySeti = urunListesiMap.entrySet();
	        System.out.println("---------------------------�R�N L�STES�---------------------------------");
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
	        System.out.println("   ********* urun giri� sayfas� ********* ");
	        System.out.println("g�ncellemek istedi�iniz �r�n id si giriniz:");
	        int arananId = scan.nextInt();
	        if (urunListesiMap.keySet().contains(arananId)) {
	            System.out.println("miktar giriniz");
	            // integer data t�r� d���nda giri� yap�l�nca exception yerine d�ng�ye girecek ve ge�erli de�eri yazd�rmas�n� isteyecek
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
	                    System.out.println("l�tfen ge�erli miktar giris yap�n�z");
	                }
	            } while (flag);

	            urunListesiMap.get(arananId).setUrunMiktar(guncelmiktar + urunListesiMap.get(arananId).getUrunMiktar());
	            //aranan id mize ait �r�n map den getirildi ve eski �r�n miktar�na eklendi
	            System.out.println("urun miktar�n�z g�ncel hale getirildi\n g�ncel miktar: " + urunListesiMap.get(arananId).getUrunMiktar());
	        } else {
	            System.out.println("arad���n�z �r�n yoktur");
	        }
	    }

	    private static void urunuRafaKoy() {
	        //urunuRafaKoy 	==> listeden urunu sececegiz ve id numarasina gore urunu rafa koyacagiz.
	        System.out.println("   ********* urunu rafa ekleme sayfas� ********* ");
	        System.out.print("Rafa yerlestirmek istediginiz urunun ID sini giriniz : ");
	        int arananId = scan.nextInt();
	        if (urunListesiMap.keySet().contains(arananId)) {
	            System.out.println("hangi rafa kald�raca��n�z� yaz�n�z:");
	            String guncelraf = scan.next();

	            urunListesiMap.get(arananId).setRaf(guncelraf);//eski raf de�erini ezip g�ncel raf de�erini girecek

	        } else {
	            System.out.println("bu �r�n depoda mevcut de�ildir malesef ");
	            urunuRafaKoy();
	        }

	    }

	    private static void urunCikisi() {
	        //urunCikisi 		==> listeden urunu sececegiz ve urunun cikis yapcagiz. burada urun listesinden sadece miktarda degisiklik yapilacak.
	        //	 						urun adedi 0dan az olamaz. 0 olunca urun tanimlamasi silinmesin. sadece miktari 0 olsun.
	        System.out.println("   ********* urun ��k�� sayfas� ********* ");
	        System.out.print("C�k�s�n� yapmak  istediginiz urunun ID sini giriniz : ");
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
	                    System.out.println("l�tfen ge�erli bir tamsay� giriniz");
	                }
	            } while (flag);

	            if ( urunListesiMap.get(arananId).getUrunMiktar() - guncelmiktar < 0) {
	                System.out.println("deponuzda bu miktarda �r�n yoktur.\n bulunan miktar: " + urunListesiMap.get(arananId).getUrunMiktar());
	            } else {
	                urunListesiMap.get(arananId).setUrunMiktar( urunListesiMap.get(arananId).getUrunMiktar() - guncelmiktar);
	                System.out.println("urun miktar�n�z g�ncel hale getirildi\n g�ncel miktar: " + urunListesiMap.get(arananId).getUrunMiktar());
	            }

	        } else {
	            System.out.println("arad���n�z �r�n yoktur");
	        }
	    }


	    private static void cikis() {
	        System.out.println(" depo dan ��k�� yapt�n�z. tekrar bekleriz..");
	    }

	}

