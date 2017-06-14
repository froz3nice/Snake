package snake;

public interface ICollection<E> {
    
   public boolean remove(Object o);
   public boolean add(E e);
   public E get(int index);
}
