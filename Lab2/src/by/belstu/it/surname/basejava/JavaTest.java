package by.belstu.it.surname.basejava;

import static java.lang.Math.log;
import static java.lang.Math.min;
/**
 @author author
 @version 1.1
 */
public class JavaTest {
    public final int a = 6;
    public static final int b = 6;
    /**
     * @param args - аргументы
     * */
    public static void main(String[] args) {
        char a = '$';
        int b = 23;
        short c = 9;
        byte d = 1;
        long e = 123;
        boolean f = true;
        double g = 12.2;
        System.out.println("String + int" + b);
        System.out.println("String + char\n" + "string" + a);
        System.out.println("String + double\n" + "str" + g);
        byte d1 = (byte) (d + b);
        System.out.println(d1);
        int b1 = (int) (g + e);
        System.out.println("double + long: " + b1);
        long e1 = b + 2147483647L;
        System.out.println("int + long: " + e1);
//        static int sint;
//        System.out.println("sint:" + sint);
        boolean f1 = true;
        boolean f2 = f1 && f;
        boolean f3 = f1 ^ f;
        System.out.println("Результат boolean = boolean && boolean: " + f2);
        System.out.println("Результат boolean = boolean ^^ boolean: " + f3);
//        boolean f4 = f2 + f3; нельзя складывать булевые значения
        long l1 = 9223372036854775807L;
        long l2 = 0x7fff_ffff_fffL;
        char c1 = 'a';
        char c2 = '\u0061';
        char c3 = 97;
        System.out.println(c1 + " " + c2 + " " + c3 + "\nSum of numbers: " + (c1 + c2 + c3));
        System.out.println("3.45 % 2.4 =  " + 3.45 % 2.4);
        System.out.println("1.0/0.0 =  " + 1.0 / 0.0);
        System.out.println("0.0/0.0 =  " + 0.0 / 0.0);
        System.out.println("log(-345) =  " + log(-345));
        System.out.println("float: " + Float.intBitsToFloat(0x7F800000));
        System.out.println("float: " + Float.intBitsToFloat(0xFF800000));
        ///////////////////////////////////////////////////////////////////
        System.out.println("Math.PI: " + Math.PI);
        System.out.println("Math.E: " + Math.E);
        System.out.println("Округление Math.PI: " + Math.round(Math.PI));
        System.out.println("Округление Math.E: " + Math.round(Math.E));
        System.out.println("Минимальное: " + Math.min(Math.PI, Math.E));
        System.out.println("Случайное число: " + Math.random());
        Boolean bool1 = false;
        Character char1 = '$';
        Integer int1 = 142;
        Byte byte1 = 127;
        Short short1 = 55;
        Long long1 = 2345123421L;
        Double double1 = 44.4;
        System.out.println("Integer: " + int1);
        System.out.println("142 >>> 1: " + (int1 >>> 1));
        System.out.println("-142 >>> 1: " + (-int1 >>> 1));
        System.out.println("142 >> 2: " + (int1 >> 2));
        System.out.println("true ~ false: " + (true ^ false)); // ^ - исключающее ИЛИ
        System.out.println("true & false: " + (true & false)); // & - И
        System.out.println(String.format("long max: %s, long min: %s, byte max: %s, byte min: %s",
                Long.MAX_VALUE, Long.MIN_VALUE, Byte.MAX_VALUE, Byte.MIN_VALUE));
        Integer intPack = 100;
        int intUnpack = intPack;
        Byte bytePack = 123;
        byte byteUnpack = bytePack;
        int i = Integer.parseInt("333");
        System.out.println(i);
        String ii = Integer.toHexString(177);
        System.out.println(ii);
        System.out.println(Integer.compare(i, b));
        System.out.println(Integer.toString(intUnpack));
        System.out.println(Integer.bitCount(intUnpack));

        String s34 = "2345";
        int s35 = Integer.parseInt(s34);
        Integer i2 = Integer.valueOf(s34);
        System.out.println("string to int: " + s35);
        System.out.println("string to Integer: " + i2);
        byte[] bt = s34.getBytes();
        System.out.println("string -> byte: " + bt);
        String btStr = new String(bt);
        System.out.println("byte -> string: " + btStr);
        String s36 = "Na zavod";
        boolean s37 = Boolean.parseBoolean(s36);
        System.out.println("1)str -> bool: " + s37);
        boolean s38 = Boolean.valueOf(s36);
        System.out.println("2)str -> bool: " + s38);
        String str1 = "Java and C#";
        String str2 = "Java and C#";

        System.out.println("==: " + str1 == str2);
        System.out.println("equals: " + str1.equals(str2));
        System.out.println("compareTo: " + str1.compareTo(str2));
        str2 = null;
        System.out.println("==: " + str1 == str2);
        System.out.println("equals: " + str1.equals(str2));
//        Exception NullPointerException
//        System.out.println("compareTo: " + str1.compareTo(str2));
        /////////////////////////////////////////
        String[] arr = str1.split(" ");
        for (String st: arr) {
            System.out.println(st);
        }
        System.out.println("contains: " + str1.contains("C#"));
        System.out.println("hashCode: " + str1.hashCode());
        System.out.println("indexOf: " + str1.indexOf("a"));
        System.out.println("length: " + str1.length());
        System.out.println("replace: " + str1.replace("Java", "C++"));
        //////////////////////////////////////////
        char[][] c11;
        char[] c22[];
        char c33[][];

        int zeroArr[] = new int [0];
        // выход за пределы
        //System.out.println(zeroArr[5]);

        // c1
        c11 = new char[3][];
        c11[0] = new char[2];
        c11[1] = new char[7];
        c11[2] = new char[4];
        System.out.println("Length array: " + c11.length);
        System.out.println("Length line 0: " + c11[0].length);
        System.out.println("Length line 1: " + c11[1].length);
        System.out.println("Length line 2: " + c11[2].length);

        c22 = new char[][] {{'1', '4', '4'}, {'7', '9', '0'}};
        c33 = new char[][] {{'1', '4', '4'}, {'7', '9', '0'}};
        boolean comRez = c22 == c33;
        c22 = c33;
        System.out.println(c22 == c33);
        for (char[] mas: c22) {
            for (char item: mas) {
                System.out.println(item);
            }
        }
        String test = "Hello Java!";
        WrapperString testWrapp = new WrapperString(test);
        testWrapp.replace('l','$');

        var testWrapp2 = new WrapperString(test) {
            @Override
            public void replace(char oldChar, char newChar) {
                System.out.println(test.replace(oldChar, newChar));
            }
            public void delete(String newChar) {
                System.out.println(test.replace(newChar, ""));
            }
        };
        testWrapp2.replace('l', '*');
        testWrapp2.delete("o");
    }
}
