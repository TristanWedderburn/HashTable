package HashTableLin;

public class HashTableLin {

    private Integer[] table;
    private int size;
    private int keys;
    private double loadf;

    public HashTableLin(int maxNum, double load) {
        //System.out.println("maxNum: "+maxNum);  
        //System.out.println("load factor: "+load);
        size = (int) (maxNum / load);

        while (isPrime(size) != true) {
            //System.out.println("size to test: "+size); 
            size++;
        }
        //System.out.println("end size: "+size);   
        table = new Integer[size];
        loadf = load;
    }

    public HashTableLin() {
        table = null;
        size = 0;
        keys = 0;
        loadf = 0.0;
    }

    private boolean isPrime(int m) {
        if (m > 2 && (m & 1) == 0) {
            return false;
        }
        for (int i = 3; i * i <= m; i += 2) {
            if (m % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void insert(int n) {//Big theta n running time, Big theta 1 space complexity

        if (isIn(n) == false) {
            System.out.println("isIn==false");
            if ((double) keys / size >= loadf) {
                rehash();//Big theta n
            }
                if (table[n % size] == null) {
                    table[n % size] = n;
                } else {
                    int linprobe = 1;
                    while (table[n % size + linprobe] != null && (n % size + linprobe) < size) {//Big theta log n
                        linprobe++;
                    }
                    if (n % size + linprobe > size) {
                        //System.out.println("failed");
                        return;
                    } else {
                        table[n % size + linprobe] = n;
                    }
                }
            
        }
        keys++;

    }

    public int insert2(int n) {//for success method
        int probes = 1;
        if (isIn(n) == false) {
            if ((double) keys / size >= loadf) {
                rehash();
            }
            if (table[n % size] == null) {
                table[n % size] = n;
            } else {
                while (((n % size) + probes) < size && table[(n % size) + probes] != null) {
                    probes++;
                }
                if ((n % size) + probes == size) {
                    //return 0;
                    //System.out.println("failed");
                } else {
                    table[(n % size) + probes] = n;
                }
            }
            keys++;
        }
        return probes;
    }

    public void rehash() {//Big theta n running time, Big theta n space complexity
        //System.out.println("REHASH REQUIRED");
        //System.out.println("initial size: "+size);
        int size2 = 2 * size;
        //System.out.println("2*initial size="+size2);

        while (isPrime(size2) != true) {//log n running time
            //System.out.println("size to test: "+size2); 
            size2++;
        }
        //System.out.println("end size: "+size2);  

        Integer[] temp = new Integer[size2];//n space complexity
        HashTableLin table2 = new HashTableLin();
        table2.table = temp;
        table2.size = temp.length;
        table2.loadf = loadf;

        int counter = keys;
        for (int i = 0; counter >= 0; i++) {//n running time
            if (i == size - 1 && table[i] == null) {
                break;
            }
            if (table[i] != null) {
                table2.insert(table[i]);
                counter--;
            }
        }
    }

//    public boolean isIn(int n) {//Big theta n running time
//        for (int i = 0; i < size; i++) {
//            if (table[i] != null && table[i] == n) {
//                return true;
//            }
//        }
//        return false;
//    }
    
    public boolean isIn(int n) {//Big theta n running time
        if(table[n % size]!=null&&table[n % size]==n){
            
            
            return true;
        }
        else{
            for (int i = n % size; table[i]!=null; i++) {
                if (i == size - 1) {
                break;
                }
                if (table[i] == n) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public void printKeys() {
        int counter = keys;
        for (int i = 0; counter >= 0; i++) {
            if (i == size - 1 && table[i] == null) {
                break;
            }
            if (table[i] != null) {
                System.out.print(table[i] + ", ");
                counter--;
            }
        }
    }

    public void printKeysAndIndexes() {
        int counter = keys;
        for (int i = 0; counter >= 0; i++) {
            if (i == size - 1 && table[i] == null) {
                break;
            }
            if (table[i] != null) {
                System.out.print("index " + i + ": " + table[i] + ", ");
                counter--;
            }
        }
    }

    public double getLoad() {
        return loadf;
    }

    public int getSize() {
        return size;
    }

    public int getKeys() {
        return keys;
    }
}
