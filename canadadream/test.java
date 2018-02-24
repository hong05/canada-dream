public class DuplicateNumber{
  public int findTheDuplicate(List<Integer> numbers){
    int highestNum = numbers.size()-1;
    int total = getSum(numbers);
    int duplicate = total - highestNum*(highestNum+1)/2;
    return duplicate;
  }

  public int getSum(List<Integer> numbers){
    int total = 0;
    for(int i:numbers){
      total = total+i;
    }
    return total;
  }

  public static void main(String a[]){
    List<Integer> numbers = new ArrayList<Integer>();
    for(int i=1;i<30;i++){
      numbers.add(i);
    }
    numbers.add(22);
    DuplicateNumber dn = new DuplicateNumber();
    System.out.println("Duplicate number is": + dn.findTheDuplicate(numbers));
  }
}
/-----------------------------------------------/

public class FindArraryIndex{
  public int findArraryIndex(int[] numbers) throws Exception{
    int endIndex = numbers.length - 1;
    int startIndex = 0;
    int leftSum = 0;
    int rightSum = 0;
    while(true){
      if(leftSum<rightSum){
        leftSum += numbers[startIndex++];
      }else{
        rightSum +=numbers[endIndex--];
      }

      if (startIndex > endIndex){
        if(leftSum == rightSum){
          break;
        }else{
          throw new Exception("please pass proper array to match the requirement");
        }
      }
    return endIndex;
    }
  }

  public static void main(String a[]){
    int[] numbers = {2,4,4,5,4,1};
    try{
      findArraryIndex(numbers);
    }catch(Exception ex){
      System.out.println(ex.getMessage());
    }
  }
}

/-----------------------------------------------/
public class StringRecurse{
  public String reverseString(String str){
    String result = "";
    if(str.length()==1){
      return str;
    }else{
      result+= str.charAt(str.length() - 1) + reverseString(str.substring(0,length()-1));
      return result;
    }
  }
  public static void main(String a[]){
    String str = "Java";
    StringRecurse sr = new StringRecurse();
    System.out.println(sr.reverseString(str));
  }
}

/-----------------------------------------------/
public class SinglyLinkedList<T>{
  private Node<T> head;

  public void add(T element){
    Node<T> nd = new Node<T>();
    nd.setValue(element);
    Node<T> temp = head;
    while(true){
      if(temp==null){
        head = nd;
        break;
      }else if(temp.getNextRef()==null){
        temp.setNextRef(nd);
      }else{
        temp = temp.getNextRef();
      }
    }
   }

   public void traverse(){
     Node<T> tmp = head;
     while(true){
       if(tmp==null){
         break;
       }
       System.out.print(tmp.getValue()+"\t");
       tmp=tmp.getNextRef();
     }
   }

   public void reverse(){
     Node<T> prev = null;
     Node<T> current = head;
     Node<T> next = null;
     while(current!= null){
       next= current.getNextRef();
       current.setNextRef(prev);
       prev=current;
       current=next;
     }
     head=prev;
   }

   public static void main(String a[]){
     SinglyLinkedList<Integer> sl = new SinglyLinkedList<Integer>();
     sl.add(3);
     sl.add(32);
     sl.tranverse();
     sl.traverse();
   }
}

class Node<T> implements Comparable<T>{
  private T value;
  private Node<T> nextRef;

  public T getValue(){
    return value;
  }
  public void setValue(T value){
    this.value = value;
  }
  public Node<T> getNextRef(){
    return nextRef;
  }
  public void setNextRef(Node<T> ref){
    this.nextRef = ref;
  }
  @Overide
  public int compareTo(T arg){
    if(arg == this.value){
      return 0;
    }else{
      return 1;
    }
  }
}
/-----------------------------------------------/

public class NumberReverse{
  public int reverseNum(int number){
    int reverse=0;
    while(number!=0){
      reverse = (reverse*10)+(number%10);
      number = number/10;
    }
    return reverse;
  }

  public static void main(String a[]){
    NumberReverse nr = new NumberReverse();
    System.out.println(nr.reverseNum(193838));
  }
}
/-----------------------------------------------/

public class IsPerfectNum{
  public boolean isPerfect(int number){
    int temp = 0;
    for(int i=1;i<number/2;i++){
      if(number%i== 0){
        temp += i;
      }
    }
    if(temp == number){
      System.out.println("It is perfect");
      return true;
    }else{
      System.out.println("It is not perfect");
      return false;
    }
  }

  public static void main(String a[]){
    IsPerfectNum ipn = new IsPerfectNum();
    System.out.println("it is perfect is:"+ipn.isPerfect(28));
  }
}

/------------ArrayList-----------------------------------/
import java.util.*;
class TestCollection{
  public static void main(String args[]){
    ArrayList<String> al = new ArrayList<String>();
    list.add("one");
    list.add("two");
    Iterator itr = list.Iterator();
    while(itr.hasNext()){
      System.out.println(itr.next());
    }
    for(String str:al){
      System.out.println(str);
    }
  }
}
/-----------------------------------------------/
class Student{
  int age;
  String name;

  Student(int age, String name){
    this.age= age;
    this.name = name;
  }
}

public class Test{
  public static void main(String args[]){
    Student s1 = new Student(13,"Josh");
    Student s2 = new Student(13,"Josh");
    Student s3 = new Student(13,"Josh");

    ArrayList<Student> al = new ArrayList<Student>();
    al.add(s1);
    al.add(s2);
    System.out.println("Element at 2nd position: "+al.get(2));

    ArrayList<Student> al2 = new ArrayList<Student>();
    al2.add(s3);
    al.addAll(al2);

    System.out.println("traversing elements in backward direction...");
    ListIterator<String> itr=al.listIterator();
    while(itr.hasPrevious()){
        System.out.println(itr.previous());
}
/*LinkedList
addFirst(Object o)	insert the given element at the beginning of a list.
addLast(Object o)	  append the given element to the end of a list.
getFirst(), getLast()

*/
/----------------HashSet-------------------------------/
import java.util.*;
class TestCollection9{
 public static void main(String args[]){
  //Creating HashSet and adding elements
  HashSet<String> set=new HashSet<String>();
  set.add("Ravi");
  set.add("Vijay");
  //Traversing elements
  Iterator<String> itr=set.iterator();
  while(itr.hasNext()){
   System.out.println(itr.next());
  }
 }
}

/*Queue : add only to the last
add(obj) insert to end
element()	 retrieves the head of this queue.
peek()	It is used to retrieves the head of this queue, or returns null if this queue is empty.
remove()	retrieves and removes the head of this queue.
poll()	retrieves and removes the head of this queue, or returns null if this queue is empty.

Deque: able to add to both head and last
offerFirst(obj) insert to head
pullLast() remove the last
*/

/* Map
Object put(Object key, Object value)	It is used to insert an entry in this map.
void putAll(Map map)	It is used to insert the specified map in this map.
Object remove(Object key)	It is used to delete an entry for the specified key.
Object get(Object key)	It is used to return the value for the specified key.
boolean containsKey(Object key)	It is used to search the specified key from this map.
Set keySet()	It is used to return the Set view containing all the keys.
Set entrySet()	It is used to return the Set view containing all the keys and values.
*/

class MapExample{
  public static void main(String args[]){
    Map<Integer,String> map = new HashMap<Integer,String>();
    map.put(100,"one");
    map.put(101,"two");
    for(Map.Entry m : map.entrySet()){
      System.out.println(m.getKey() + m.getValue());
    }

    Set set = map.entrySet();
    Iterator itr = set.Iterator();
    while(itr.hasNext()){
      Map.Entry entry = (Map.entry) itr.next();
      System.out.println(entry.getKey()+entry.getValue());
    }
    map.remove(101);
  }
}

/-----------------------------------------------/
class Book{
  int id;
  String name, author;
  public Book(int id,String name,String author){
    this.id = id;
    this.name = name;
    this.author = author;
  }
}
public class MapBook{
  public static void main(String[] args){
    Map<Integer,Book> map = new HashMap<Integer,Book>();
    Book book1 = new Book(12321,"learn C","Rollman");
    map.put(1,book1);

    for(Map.Entry<Integer,Book> entry: map.entrySet()){
      int key = entry.getKey();
      Book book = entry.getValue();
      System.out.println("key"+" "+key+" Book "+book.name);
    }
  }
  //for ordered map, like LinkedHashMap, TreeMap, we have map.get(key)
}
/* HashMap	VS TreeMap
1) HashMap can contain one null key.	TreeMap can not contain any null key.
2) HashMap maintains no order.	TreeMap maintains ascending order.*/



/----------------EnumSet-------------------------------/
enum days{
  SUNDAY,MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY
}
public class EnumSetExample{
  public static void main(String[] args){
    Set<days> daySet = EnumSet.of(days.TUESDAY,days.WEDNESDAY);
    Set<days> allDaySet = EnumSet.allOf(days.class);

    Iterator itr = set.iterator();
    while(itr.hasNext()){
      System.out.println(itr.next());
    }
  }
}

/----------------Collection-------------/
public class CollectionExp{
  public static void main(String a[]){
    List<String> myList  = new ArrayList<String>();
    myList.add("one");
    Collections.addAll(myList,"two","three");
    String[] strArr = {"four","five"};
    Collections.addAll(myList,strArr);
    System.out.println(myList);

    List<Integer> numList = new ArrayList<Integer>();
    numList.add(1);
    numList.add(2);
    System.out.println("max value is" + numList.max());

    Collections.sort(numList);
  }
}

/-------------Comparable------------------/
class Student implements Comparable<Student>{
  int age;
  String name;
  public Student(int age,String name){
    this.age=age;
    this.name = name;
  }
  public int compareTo(Student st){
    if(age == st.age){
      return 0;
    }else if(age>st.age){
      return 1;
    }else{
      return -1;
    }
  }
}

public class TestStudent{
  public static void main(String args[]){
    ArrayList<Student> stl = new ArrayList<Student>();
    stl.add(new Student(20,"kaka"));
    stl.add(new Student(21,"aka"));

    Collections.sort(stl);
    for(Student st: stl){
      System.out.println(st.name+" "+st.age);
    }
  }
}

/-------------Comparator------------------/
class NameComparator implements Comparator{
  public int compare(Object o1, Object o2){
    Student s1 = (Student)s1;
    Student s2 = (Student)s2;

    return s1.name()
  }
}










/-----------------------------------------------/
