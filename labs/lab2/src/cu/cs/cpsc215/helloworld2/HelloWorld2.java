package cu.cs.cpsc215.helloworld2;

public class HelloWorld2 {
	public static void main(String[] args) {
		
		System.out.println("Hello World!!");
		
		int i;
        for(i=0 ; i<args.length; ++i)
        {
            System.out.println(args[i]);
        }
        System.out.println(args.length);
// or one can do
        /*
		for (String s : args)
		//agrs is an array string
		 * the " : " exausts all elements of an object
		 * so in this, it will print every element in the String array of agrs
        {
            System.out.println(s);
        }
         */
	}
}