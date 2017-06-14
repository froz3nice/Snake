package snake;

import java.util.Arrays;
import java.util.Collection;

public class Array1<E> implements ICollection {

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    transient Object[] data;
    private int size;
    private static final Object[] emptyData = {};
    protected transient int modCount = 0;

    public Array1() {
        this.data = emptyData;
    }

    public int size() {
        return this.size;
    }

    public Array1(Collection<? extends E> c) {
        data = c.toArray();
        if ((size = data.length) != 0) {
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (data.getClass() != Object[].class) {
                data = Arrays.copyOf(data, size, Object[].class);
            }
        } else {
            // replace with empty array.
            this.data = emptyData;
        }
    }
    
    @Override
    public boolean add(Object e) {
        ensureCapacityInternal(size + 1);  
        data[size++] = e;
        return true;
    }

    public E get(int index) {
        return (E) data[index];
    }
  
    public E remo(int index){
        // rangeCheck(index);

         modCount++;
        E oldValue = (E) data[index];
 
        int numMoved = size - index - 1;
         if (numMoved > 0)
             System.arraycopy(data, index+1, data, index,
                              numMoved);
        data[--size] = null; // Let gc do its work
      
        return oldValue;
    }
    
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++) {
                if (data[index] == null) {
                    fastRemove(index);
                    return true;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (o.equals(data[index])) {
                    fastRemove(index);
                    return true;
                }
            }
        }
        return false;
    }

    private void fastRemove(int index) {
        modCount++;

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index,
                    numMoved);
        }
        data[--size] = null; // clear to let GC do its work
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(data, size, a.getClass());
        }
        System.arraycopy(data, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    public Object clone() {
        try {
            Array1<?> v = (Array1<?>) super.clone();
            v.data = Arrays.copyOf(data, size);
            return v;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }

        size = 0;
    }

    private void grow(int minCapacity) {
        int oldCapacity = data.length;
        
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = hugeCapacity(minCapacity);
        }
        data = Arrays.copyOf(data, newCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {

        if (minCapacity - data.length > 0) {
            grow(minCapacity);
        }
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
        {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE)
                ? Integer.MAX_VALUE
                : MAX_ARRAY_SIZE;
    }

    public void add(int index, E element) {
        rangeCheckForAdd(index);
        System.arraycopy(data, index, data, index + 1,
                size - index);
        data[index] = element;
        size++;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
    }

    private void ensureCapacityInternal(int minCapacity) {
        modCount++;
        if (data == emptyData) {
            minCapacity = Math.max(10, minCapacity);
        }
        ensureExplicitCapacity(minCapacity);
    }

}