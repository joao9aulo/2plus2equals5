import java.lang.reflect.Field;

public class New2Plus2 {
    public static void main(String[] args) throws Exception {
        Class usf = Class.forName("sun.misc.Unsafe");//pega o objeto que representa a classe Unsafe
        Field unsafeField = usf.getDeclaredField("theUnsafe");//pega o campo theUnsafe da classe Unsafe
        unsafeField.setAccessible(true);//define o campo theUnsafe como público
        sun.misc.Unsafe unsafe = (sun.misc.Unsafe)unsafeField.get(null);//pega o valor do campo theUnsafe, como ele é static é passado o parâmetro null
        Class<?> clazz = Class.forName("java.lang.Integer$IntegerCache");// pega o objeto que representa a classe IntegerCache
        Field field = clazz.getDeclaredField("cache");//pega o campo cache da classe IntegerCache
        Integer[] cache = (Integer[])unsafe.getObject(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field));//utiliza a classe Unsafe para pegar o cache de Integer
        cache[132] = cache[133];// troca o valor 4 pelo 5
        System.out.printf("2+2 = %d",2 + 2);
    }
}