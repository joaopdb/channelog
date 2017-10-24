

package sx.blah.discord.util.cache;

import com.koloboke.collect.impl.AbstractLongKeyView;
import com.koloboke.collect.impl.AbstractObjValueView;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.function.Consumer;
import com.koloboke.collect.Equivalence;
import javax.annotation.Generated;
import com.koloboke.collect.impl.hash.Hash;
import com.koloboke.collect.hash.HashConfig;
import com.koloboke.collect.impl.hash.HashConfigWrapper;
import com.koloboke.collect.set.hash.HashLongSet;
import com.koloboke.collect.impl.InternalLongCollectionOps;
import com.koloboke.collect.impl.hash.LHash;
import com.koloboke.collect.impl.hash.LHashCapacities;
import com.koloboke.collect.impl.LongArrays;
import com.koloboke.collect.LongCollection;
import java.util.function.LongConsumer;
import com.koloboke.collect.LongCursor;
import com.koloboke.collect.LongIterator;
import com.koloboke.function.LongObjConsumer;
import com.koloboke.function.LongObjPredicate;
import java.util.function.LongPredicate;
import com.koloboke.collect.set.LongSet;
import com.koloboke.collect.impl.Maths;
import java.util.NoSuchElementException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.koloboke.collect.ObjCollection;
import com.koloboke.collect.ObjCursor;
import com.koloboke.collect.ObjIterator;
import com.koloboke.collect.set.ObjSet;
import java.util.function.Predicate;
import java.util.Random;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.concurrent.ThreadLocalRandom;

@Generated(value = "com.koloboke.compile.processor.KolobokeCollectionProcessor")
@SuppressFBWarnings(value = { "IA_AMBIGUOUS_INVOCATION_OF_INHERITED_OR_OUTER_METHOD" })
@SuppressWarnings(value = { "all" , "unsafe" , "deprecation" , "overloads" , "rawtypes" })
final class KolobokeLongMap<T>  extends LongMap<T> {
    KolobokeLongMap(int expectedSize) {
        this.init(DEFAULT_CONFIG_WRAPPER, expectedSize);
    }

    static void verifyConfig(HashConfig config) {
        if ((config.getGrowthFactor()) != 2.0) {
            throw new IllegalArgumentException(((((((config + " passed, HashConfig for a hashtable\n") + "implementation with linear probing must have growthFactor of 2.0.\n") + "A Koloboke Compile-generated hashtable implementation could have\n") + "a different growth factor, if the implemented type is annotated with\n") + "@com.koloboke.compile.hash.algo.openaddressing.QuadraticProbing or\n") + "@com.koloboke.compile.hash.algo.openaddressing.DoubleHashing"));
        } 
    }

    @Nonnull
    public final HashConfig hashConfig() {
        return configWrapper().config();
    }

    long freeValue;

    T[] values;

    long[] set;

    public final boolean noRemoved() {
        return true;
    }

    public final boolean isEmpty() {
        return (size()) == 0;
    }

    public final int freeSlots() {
        return (capacity()) - (size());
    }

    private HashConfigWrapper configWrapper;

    @Nonnull
    public long[] keys() {
        return set;
    }

    public boolean containsKey(long key) {
        return contains(key);
    }

    int size;

    public final int removedSlots() {
        return 0;
    }

    private int maxSize;

    public int capacity() {
        return set.length;
    }

    @Nonnull
    public HashLongSet keySet() {
        return new KolobokeLongMap.KeyView();
    }

    public final double currentLoad() {
        return ((double) (size())) / ((double) (capacity()));
    }

    final void init(HashConfigWrapper configWrapper, int size, long freeValue) {
        KolobokeLongMap.this.freeValue = freeValue;
        init(configWrapper, size);
    }

    public void forEach(Consumer<? super Long> action) {
        if (action == null)
            throw new NullPointerException();
        
        if (KolobokeLongMap.this.isEmpty())
            return ;
        
        long free = freeValue;
        long[] keys = set;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                action.accept(key);
            } 
        }
    }

    public final HashConfigWrapper configWrapper() {
        return configWrapper;
    }

    class KeyView extends AbstractLongKeyView implements HashLongSet , InternalLongCollectionOps , KolobokeLongMap.Support.SeparateKVLongLHash {
        @Nonnull
        @Override
        public HashConfig hashConfig() {
            return KolobokeLongMap.this.hashConfig();
        }

        @Override
        public HashConfigWrapper configWrapper() {
            return KolobokeLongMap.this.configWrapper();
        }

        @Override
        public int size() {
            return KolobokeLongMap.this.size();
        }

        @Override
        public double currentLoad() {
            return KolobokeLongMap.this.currentLoad();
        }

        @Override
        public long freeValue() {
            return KolobokeLongMap.this.freeValue();
        }

        @Override
        public boolean supportRemoved() {
            return KolobokeLongMap.this.supportRemoved();
        }

        @Override
        public long removedValue() {
            return KolobokeLongMap.this.removedValue();
        }

        @Nonnull
        @Override
        public long[] keys() {
            return KolobokeLongMap.this.keys();
        }

        @Override
        public int capacity() {
            return KolobokeLongMap.this.capacity();
        }

        @Override
        public int freeSlots() {
            return KolobokeLongMap.this.freeSlots();
        }

        @Override
        public boolean noRemoved() {
            return KolobokeLongMap.this.noRemoved();
        }

        @Override
        public int removedSlots() {
            return KolobokeLongMap.this.removedSlots();
        }

        @Override
        public int modCount() {
            return 0;
        }

        @Override
        public final boolean contains(Object o) {
            return KolobokeLongMap.this.contains(o);
        }

        @Override
        public boolean contains(long key) {
            return KolobokeLongMap.this.contains(key);
        }

        @Override
        public void forEach(Consumer<? super Long> action) {
            KolobokeLongMap.this.forEach(action);
        }

        @Override
        public void forEach(LongConsumer action) {
            KolobokeLongMap.this.forEach(action);
        }

        @Override
        public boolean forEachWhile(LongPredicate predicate) {
            return KolobokeLongMap.this.forEachWhile(predicate);
        }

        @Override
        public boolean allContainingIn(LongCollection c) {
            return KolobokeLongMap.this.allContainingIn(c);
        }

        @Override
        public boolean reverseAddAllTo(LongCollection c) {
            return KolobokeLongMap.this.reverseAddAllTo(c);
        }

        @Override
        public boolean reverseRemoveAllFrom(LongSet s) {
            return KolobokeLongMap.this.reverseRemoveAllFrom(s);
        }

        @Override
        @Nonnull
        public LongIterator iterator() {
            return KolobokeLongMap.this.iterator();
        }

        @Override
        @Nonnull
        public LongCursor cursor() {
            return setCursor();
        }

        @Override
        @Nonnull
        public Object[] toArray() {
            return KolobokeLongMap.this.toArray();
        }

        @Override
        @Nonnull
        public <T2>  T2[] toArray(@Nonnull
        T2[] a) {
            return KolobokeLongMap.this.toArray(a);
        }

        @Override
        public long[] toLongArray() {
            return KolobokeLongMap.this.toLongArray();
        }

        @Override
        public long[] toArray(long[] a) {
            return KolobokeLongMap.this.toArray(a);
        }

        @Override
        public int hashCode() {
            return setHashCode();
        }

        @Override
        public String toString() {
            return setToString();
        }

        @Override
        public boolean shrink() {
            return KolobokeLongMap.this.shrink();
        }

        @Override
        public final boolean remove(Object o) {
            return justRemove(((Long) (o)));
        }

        @Override
        public boolean removeLong(long v) {
            return justRemove(v);
        }

        @Override
        public boolean removeIf(Predicate<? super Long> filter) {
            return KolobokeLongMap.this.removeIf(filter);
        }

        @Override
        public boolean removeIf(LongPredicate filter) {
            return KolobokeLongMap.this.removeIf(filter);
        }

        @Override
        public boolean removeAll(@Nonnull
        Collection<?> c) {
            if (c instanceof LongCollection) {
                if (c instanceof InternalLongCollectionOps) {
                    InternalLongCollectionOps c2 = ((InternalLongCollectionOps) (c));
                    if ((c2.size()) < (KolobokeLongMap.KeyView.this.size())) {
                        return c2.reverseRemoveAllFrom(KolobokeLongMap.KeyView.this);
                    } 
                } 
                return KolobokeLongMap.this.removeAll(KolobokeLongMap.KeyView.this, ((LongCollection) (c)));
            } 
            return KolobokeLongMap.this.removeAll(KolobokeLongMap.KeyView.this, c);
        }

        @Override
        public boolean retainAll(@Nonnull
        Collection<?> c) {
            return KolobokeLongMap.this.retainAll(KolobokeLongMap.KeyView.this, c);
        }

        @Override
        public void clear() {
            KolobokeLongMap.this.clear();
        }
    }

    boolean valueEquals(@Nonnull
    T a, @Nullable
    T b) {
        return a.equals(b);
    }

    public long freeValue() {
        return freeValue;
    }

    @Override
    public final int size() {
        return size;
    }

    public boolean supportRemoved() {
        return false;
    }

    public void forEach(LongConsumer action) {
        if (action == null)
            throw new NullPointerException();
        
        if (KolobokeLongMap.this.isEmpty())
            return ;
        
        long free = freeValue;
        long[] keys = set;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                action.accept(key);
            } 
        }
    }

    public long removedValue() {
        throw new UnsupportedOperationException();
    }

    int valueIndex(@Nullable
    Object value) {
        if (value == null)
            return nullValueIndex();
        
        if (KolobokeLongMap.this.isEmpty())
            return -1;
        
        T val = ((T) (value));
        int index = -1;
        long free = freeValue;
        long[] keys = set;
        T[] vals = values;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            if ((keys[i]) != free) {
                if (valueEquals(val, vals[i])) {
                    index = i;
                    break;
                } 
            } 
        }
        return index;
    }

    @Nonnull
    public Equivalence<T> valueEquivalence() {
        return Equivalence.defaultEquality();
    }

    public boolean contains(Object key) {
        return contains(((Long) (key)).longValue());
    }

    public boolean contains(long key) {
        return (index(key)) >= 0;
    }

    final void init(HashConfigWrapper configWrapper, int size) {
        KolobokeLongMap.verifyConfig(configWrapper.config());
        KolobokeLongMap.this.configWrapper = configWrapper;
        KolobokeLongMap.this.size = 0;
        internalInit(targetCapacity(size));
    }

    public boolean forEachWhile(LongPredicate predicate) {
        if (predicate == null)
            throw new NullPointerException();
        
        if (KolobokeLongMap.this.isEmpty())
            return true;
        
        boolean terminated = false;
        long free = freeValue;
        long[] keys = set;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                if (!(predicate.test(key))) {
                    terminated = true;
                    break;
                } 
            } 
        }
        return !terminated;
    }

    int index(long key) {
        long free;
        if (key != (free = freeValue)) {
            long[] keys = set;
            int capacityMask;
            int index;
            long cur;
            if ((cur = keys[(index = (LHash.SeparateKVLongKeyMixing.mix(key)) & (capacityMask = (keys.length) - 1))]) == key) {
                return index;
            } else {
                if (cur == free) {
                    return -1;
                } else {
                    while (true) {
                        if ((cur = keys[(index = (index - 1) & capacityMask)]) == key) {
                            return index;
                        } else if (cur == free) {
                            return -1;
                        } 
                    }
                }
            }
        } else {
            return -1;
        }
    }

    private void internalInit(int capacity) {
        assert Maths.isPowerOf2(capacity);
        maxSize = maxSize(capacity);
        allocateArrays(capacity);
    }

    private int nullValueIndex() {
        if (KolobokeLongMap.this.isEmpty())
            return -1;
        
        int index = -1;
        long free = freeValue;
        long[] keys = set;
        T[] vals = values;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            if ((keys[i]) != free) {
                if ((vals[i]) == null) {
                    index = i;
                    break;
                } 
            } 
        }
        return index;
    }

    private int maxSize(int capacity) {
        return !(isMaxCapacity(capacity)) ? configWrapper.maxSize(capacity) : capacity - 1;
    }

    public boolean allContainingIn(LongCollection c) {
        if (KolobokeLongMap.this.isEmpty())
            return true;
        
        boolean containsAll = true;
        long free = freeValue;
        long[] keys = set;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                if (!(c.contains(key))) {
                    containsAll = false;
                    break;
                } 
            } 
        }
        return containsAll;
    }

    @Override
    public T get(long key) {
        int index = index(key);
        if (index >= 0) {
            return values[index];
        } else {
            return null;
        }
    }

    public boolean containsValue(Object value) {
        return (valueIndex(value)) >= 0;
    }

    private long findNewFreeOrRemoved() {
        long free = KolobokeLongMap.this.freeValue;
        Random random = ThreadLocalRandom.current();
        long newFree;
        {
            do {
                newFree = ((long) (random.nextLong()));
            } while ((newFree == free) || ((index(newFree)) >= 0) );
        }
        return newFree;
    }

    boolean removeValue(@Nullable
    Object value) {
        int index = valueIndex(value);
        if (index >= 0) {
            removeAt(index);
            return true;
        } else {
            return false;
        }
    }

    public boolean reverseAddAllTo(LongCollection c) {
        if (KolobokeLongMap.this.isEmpty())
            return false;
        
        boolean changed = false;
        long free = freeValue;
        long[] keys = set;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                changed |= c.add(key);
            } 
        }
        return changed;
    }

    int insert(long key, T value) {
        long free;
        if (key == (free = freeValue)) {
            free = changeFree();
        } 
        long[] keys = set;
        int capacityMask;
        int index;
        long cur;
        keyAbsent : if ((cur = keys[(index = (LHash.SeparateKVLongKeyMixing.mix(key)) & (capacityMask = (keys.length) - 1))]) != free) {
            if (cur == key) {
                return index;
            } else {
                while (true) {
                    if ((cur = keys[(index = (index - 1) & capacityMask)]) == free) {
                        break keyAbsent;
                    } else if (cur == key) {
                        return index;
                    } 
                }
            }
        } 
        keys[index] = key;
        values[index] = value;
        postInsertHook();
        return -1;
    }

    long changeFree() {
        long newFree = findNewFreeOrRemoved();
        LongArrays.replaceAll(set, freeValue, newFree);
        KolobokeLongMap.this.freeValue = newFree;
        return newFree;
    }

    final void initForRehash(int newCapacity) {
        internalInit(newCapacity);
    }

    public boolean reverseRemoveAllFrom(LongSet s) {
        if ((KolobokeLongMap.this.isEmpty()) || (s.isEmpty()))
            return false;
        
        boolean changed = false;
        long free = freeValue;
        long[] keys = set;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                changed |= s.removeLong(key);
            } 
        }
        return changed;
    }

    private void _MutableSeparateKVLongLHashSO_allocateArrays(int capacity) {
        set = new long[capacity];
        if ((freeValue) != 0)
            Arrays.fill(set, freeValue);
        
    }

    private void _MutableLHash_clear() {
        size = 0;
    }

    private void _MutableSeparateKVLongLHashSO_clear() {
        _MutableLHash_clear();
        Arrays.fill(set, freeValue);
    }

    @Override
    public void forEach(LongObjConsumer<? super T> action) {
        if (action == null)
            throw new NullPointerException();
        
        if (KolobokeLongMap.this.isEmpty())
            return ;
        
        long free = freeValue;
        long[] keys = set;
        T[] vals = values;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                action.accept(key, vals[i]);
            } 
        }
    }

    public boolean shrink() {
        int newCapacity = targetCapacity(size);
        if (newCapacity < (capacity())) {
            rehash(newCapacity);
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings(value = "unchecked")
    void allocateArrays(int capacity) {
        _MutableSeparateKVLongLHashSO_allocateArrays(capacity);
        values = ((T[]) (new Object[capacity]));
    }

    private void _MutableLHashSeparateKVLongObjMapSO_clear() {
        _MutableSeparateKVLongLHashSO_clear();
        Arrays.fill(values, null);
    }

    @Nonnull
    public Object[] toArray() {
        int size = size();
        Object[] result = new Object[size];
        if (size == 0)
            return result;
        
        int resultIndex = 0;
        long free = freeValue;
        long[] keys = set;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                result[(resultIndex++)] = key;
            } 
        }
        return result;
    }

    @Override
    public boolean forEachWhile(LongObjPredicate<? super T> predicate) {
        if (predicate == null)
            throw new NullPointerException();
        
        if (KolobokeLongMap.this.isEmpty())
            return true;
        
        boolean terminated = false;
        long free = freeValue;
        long[] keys = set;
        T[] vals = values;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                if (!(predicate.test(key, vals[i]))) {
                    terminated = true;
                    break;
                } 
            } 
        }
        return !terminated;
    }

    @SuppressWarnings(value = "unchecked")
    @Nonnull
    public <T2>  T2[] toArray(@Nonnull
    T2[] a) {
        int size = size();
        if ((a.length) < size) {
            Class<?> elementType = a.getClass().getComponentType();
            a = ((T2[]) (Array.newInstance(elementType, size)));
        } 
        if (size == 0) {
            if ((a.length) > 0)
                a[0] = null;
            
            return a;
        } 
        int resultIndex = 0;
        long free = freeValue;
        long[] keys = set;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                a[(resultIndex++)] = ((T2) (Long.valueOf(key)));
            } 
        }
        if ((a.length) > resultIndex)
            a[resultIndex] = null;
        
        return a;
    }

    final void postRemoveHook() {
        (size)--;
    }

    final void postInsertHook() {
        if ((++(size)) > (maxSize)) {
            int capacity = capacity();
            if (!(isMaxCapacity(capacity))) {
                rehash((capacity << 1));
            } 
        } 
    }

    @Nonnull
    public long[] toLongArray() {
        int size = size();
        long[] result = new long[size];
        if (size == 0)
            return result;
        
        int resultIndex = 0;
        long free = freeValue;
        long[] keys = set;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                result[(resultIndex++)] = key;
            } 
        }
        return result;
    }

    boolean doubleSizedArrays() {
        return false;
    }

    private int targetCapacity(int size) {
        return LHashCapacities.capacity(configWrapper, size, doubleSizedArrays());
    }

    private boolean isMaxCapacity(int capacity) {
        return LHashCapacities.isMaxCapacity(capacity, doubleSizedArrays());
    }

    @Nonnull
    public long[] toArray(long[] a) {
        int size = size();
        if ((a.length) < size)
            a = new long[size];
        
        if (size == 0) {
            if ((a.length) > 0)
                a[0] = 0L;
            
            return a;
        } 
        int resultIndex = 0;
        long free = freeValue;
        long[] keys = set;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                a[(resultIndex++)] = key;
            } 
        }
        if ((a.length) > resultIndex)
            a[resultIndex] = 0L;
        
        return a;
    }

    public int setHashCode() {
        int hashCode = 0;
        long free = freeValue;
        long[] keys = set;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                hashCode += ((int) (key ^ (key >>> 32)));
            } 
        }
        return hashCode;
    }

    @SuppressFBWarnings(value = "EC_UNRELATED_TYPES_USING_POINTER_EQUALITY")
    public String setToString() {
        if (KolobokeLongMap.this.isEmpty())
            return "[]";
        
        StringBuilder sb = new StringBuilder();
        int elementCount = 0;
        long free = freeValue;
        long[] keys = set;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                sb.append(' ').append(key).append(',');
                if ((++elementCount) == 8) {
                    int expectedLength = (sb.length()) * ((size()) / 8);
                    sb.ensureCapacity((expectedLength + (expectedLength / 2)));
                } 
            } 
        }
        sb.setCharAt(0, '[');
        sb.setCharAt(((sb.length()) - 1), ']');
        return sb.toString();
    }

    @Override
    @Nonnull
    public ObjCollection<T> values() {
        return new ValueView();
    }

    @SuppressFBWarnings(value = "EC_UNRELATED_TYPES_USING_POINTER_EQUALITY")
    @Override
    public String toString() {
        if (KolobokeLongMap.this.isEmpty())
            return "{}";
        
        StringBuilder sb = new StringBuilder();
        int elementCount = 0;
        long free = freeValue;
        long[] keys = set;
        T[] vals = values;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                sb.append(' ');
                sb.append(key);
                sb.append('=');
                Object val = vals[i];
                sb.append((val != ((Object) (KolobokeLongMap.this)) ? val : "(this Map)"));
                sb.append(',');
                if ((++elementCount) == 8) {
                    int expectedLength = (sb.length()) * ((size()) / 8);
                    sb.ensureCapacity((expectedLength + (expectedLength / 2)));
                } 
            } 
        }
        sb.setCharAt(0, '{');
        sb.setCharAt(((sb.length()) - 1), '}');
        return sb.toString();
    }

    void rehash(int newCapacity) {
        long free = freeValue;
        long[] keys = set;
        T[] vals = values;
        initForRehash(newCapacity);
        long[] newKeys = set;
        int capacityMask = (newKeys.length) - 1;
        T[] newVals = values;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                int index;
                if ((newKeys[(index = (LHash.SeparateKVLongKeyMixing.mix(key)) & capacityMask)]) != free) {
                    while (true) {
                        if ((newKeys[(index = (index - 1) & capacityMask)]) == free) {
                            break;
                        } 
                    }
                } 
                newKeys[index] = key;
                newVals[index] = vals[i];
            } 
        }
    }

    @Override
    public T put(long key, T value) {
        int index = insert(key, value);
        if (index < 0) {
            return null;
        } else {
            T[] vals = values;
            T prevValue = vals[index];
            vals[index] = value;
            return prevValue;
        }
    }

    class NoRemovedIterator implements LongIterator {
        long[] keys;

        final long free;

        final int capacityMask;

        int index = -1;

        int nextIndex;

        long next;

        NoRemovedIterator() {
            long[] keys = KolobokeLongMap.NoRemovedIterator.this.keys = set;
            capacityMask = (keys.length) - 1;
            long free = this.free = freeValue;
            int nextI = keys.length;
            while ((--nextI) >= 0) {
                long key;
                if ((key = keys[nextI]) != free) {
                    next = key;
                    break;
                } 
            }
            nextIndex = nextI;
        }

        @Override
        public long nextLong() {
            int nextI;
            if ((nextI = nextIndex) >= 0) {
                index = nextI;
                long[] keys = KolobokeLongMap.NoRemovedIterator.this.keys;
                long free = KolobokeLongMap.NoRemovedIterator.this.free;
                long prev = next;
                while ((--nextI) >= 0) {
                    long key;
                    if ((key = keys[nextI]) != free) {
                        next = key;
                        break;
                    } 
                }
                nextIndex = nextI;
                return prev;
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super Long> action) {
            if (action == null)
                throw new NullPointerException();
            
            long[] keys = KolobokeLongMap.NoRemovedIterator.this.keys;
            long free = KolobokeLongMap.NoRemovedIterator.this.free;
            int nextI = nextIndex;
            for (int i = nextI; i >= 0; i--) {
                long key;
                if ((key = keys[i]) != free) {
                    action.accept(key);
                } 
            }
            if (nextI != (nextIndex)) {
                throw new ConcurrentModificationException();
            } 
            index = nextIndex = -1;
        }

        @Override
        public void forEachRemaining(LongConsumer action) {
            if (action == null)
                throw new NullPointerException();
            
            long[] keys = KolobokeLongMap.NoRemovedIterator.this.keys;
            long free = KolobokeLongMap.NoRemovedIterator.this.free;
            int nextI = nextIndex;
            for (int i = nextI; i >= 0; i--) {
                long key;
                if ((key = keys[i]) != free) {
                    action.accept(key);
                } 
            }
            if (nextI != (nextIndex)) {
                throw new ConcurrentModificationException();
            } 
            index = nextIndex = -1;
        }

        @Override
        public boolean hasNext() {
            return (nextIndex) >= 0;
        }

        @Override
        public Long next() {
            return nextLong();
        }

        @Override
        public void remove() {
            int index;
            if ((index = KolobokeLongMap.NoRemovedIterator.this.index) >= 0) {
                KolobokeLongMap.NoRemovedIterator.this.index = -1;
                long[] keys = KolobokeLongMap.NoRemovedIterator.this.keys;
                if (keys == (set)) {
                    int capacityMask = KolobokeLongMap.NoRemovedIterator.this.capacityMask;
                    int indexToRemove = index;
                    int indexToShift = indexToRemove;
                    int shiftDistance = 1;
                    while (true) {
                        indexToShift = (indexToShift - 1) & capacityMask;
                        long keyToShift;
                        if ((keyToShift = keys[indexToShift]) == (free)) {
                            break;
                        } 
                        if ((((LHash.SeparateKVLongKeyMixing.mix(keyToShift)) - indexToShift) & capacityMask) >= shiftDistance) {
                            if ((KolobokeLongMap.NoRemovedIterator.this.keys) == keys) {
                                if (indexToShift > indexToRemove) {
                                    int slotsToCopy;
                                    if ((slotsToCopy = (nextIndex) + 1) > 0) {
                                        KolobokeLongMap.NoRemovedIterator.this.keys = Arrays.copyOf(keys, slotsToCopy);
                                        if (indexToRemove < slotsToCopy) {
                                            KolobokeLongMap.NoRemovedIterator.this.keys[indexToRemove] = free;
                                        } 
                                    } 
                                } else if (indexToRemove == index) {
                                    KolobokeLongMap.NoRemovedIterator.this.nextIndex = index;
                                    if (indexToShift < (index - 1)) {
                                        KolobokeLongMap.NoRemovedIterator.this.next = keyToShift;
                                    } 
                                } 
                            } 
                            keys[indexToRemove] = keyToShift;
                            indexToRemove = indexToShift;
                            shiftDistance = 1;
                        } else {
                            shiftDistance++;
                            if (indexToShift == (1 + index)) {
                                throw new ConcurrentModificationException();
                            } 
                        }
                    }
                    keys[indexToRemove] = free;
                    postRemoveHook();
                } else {
                    justRemove(keys[index]);
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    class NoRemovedCursor implements LongCursor {
        long[] keys;

        final long free;

        final int capacityMask;

        int index;

        long curKey;

        NoRemovedCursor() {
            long[] keys = KolobokeLongMap.NoRemovedCursor.this.keys = set;
            capacityMask = (keys.length) - 1;
            index = keys.length;
            long free = this.free = freeValue;
            curKey = free;
        }

        @Override
        public void forEachForward(LongConsumer action) {
            if (action == null)
                throw new NullPointerException();
            
            long[] keys = KolobokeLongMap.NoRemovedCursor.this.keys;
            long free = KolobokeLongMap.NoRemovedCursor.this.free;
            int index = KolobokeLongMap.NoRemovedCursor.this.index;
            for (int i = index - 1; i >= 0; i--) {
                long key;
                if ((key = keys[i]) != free) {
                    action.accept(key);
                } 
            }
            if (index != (KolobokeLongMap.NoRemovedCursor.this.index)) {
                throw new ConcurrentModificationException();
            } 
            KolobokeLongMap.NoRemovedCursor.this.index = -1;
            curKey = free;
        }

        @Override
        public long elem() {
            long curKey;
            if ((curKey = KolobokeLongMap.NoRemovedCursor.this.curKey) != (free)) {
                return curKey;
            } else {
                throw new IllegalStateException();
            }
        }

        @Override
        public boolean moveNext() {
            long[] keys = KolobokeLongMap.NoRemovedCursor.this.keys;
            long free = KolobokeLongMap.NoRemovedCursor.this.free;
            for (int i = (index) - 1; i >= 0; i--) {
                long key;
                if ((key = keys[i]) != free) {
                    index = i;
                    curKey = key;
                    return true;
                } 
            }
            curKey = free;
            index = -1;
            return false;
        }

        @Override
        public void remove() {
            long curKey;
            long free;
            if ((curKey = KolobokeLongMap.NoRemovedCursor.this.curKey) != (free = KolobokeLongMap.NoRemovedCursor.this.free)) {
                KolobokeLongMap.NoRemovedCursor.this.curKey = free;
                int index = KolobokeLongMap.NoRemovedCursor.this.index;
                long[] keys = KolobokeLongMap.NoRemovedCursor.this.keys;
                if (keys == (set)) {
                    int capacityMask = KolobokeLongMap.NoRemovedCursor.this.capacityMask;
                    int indexToRemove = index;
                    int indexToShift = indexToRemove;
                    int shiftDistance = 1;
                    while (true) {
                        indexToShift = (indexToShift - 1) & capacityMask;
                        long keyToShift;
                        if ((keyToShift = keys[indexToShift]) == free) {
                            break;
                        } 
                        if ((((LHash.SeparateKVLongKeyMixing.mix(keyToShift)) - indexToShift) & capacityMask) >= shiftDistance) {
                            if ((KolobokeLongMap.NoRemovedCursor.this.keys) == keys) {
                                if (indexToShift > indexToRemove) {
                                    int slotsToCopy;
                                    if ((slotsToCopy = index) > 0) {
                                        KolobokeLongMap.NoRemovedCursor.this.keys = Arrays.copyOf(keys, slotsToCopy);
                                        if (indexToRemove < slotsToCopy) {
                                            KolobokeLongMap.NoRemovedCursor.this.keys[indexToRemove] = free;
                                        } 
                                    } 
                                } else if (indexToRemove == index) {
                                    KolobokeLongMap.NoRemovedCursor.this.index = ++index;
                                } 
                            } 
                            keys[indexToRemove] = keyToShift;
                            indexToRemove = indexToShift;
                            shiftDistance = 1;
                        } else {
                            shiftDistance++;
                            if (indexToShift == (1 + index)) {
                                throw new ConcurrentModificationException();
                            } 
                        }
                    }
                    keys[indexToRemove] = free;
                    postRemoveHook();
                } else {
                    justRemove(curKey);
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    @Override
    public void clear() {
        doClear();
    }

    private void doClear() {
        _MutableLHashSeparateKVLongObjMapSO_clear();
    }

    void removeAt(int index) {
        long free = freeValue;
        long[] keys = set;
        T[] vals = values;
        int capacityMask = (keys.length) - 1;
        int indexToRemove = index;
        int indexToShift = indexToRemove;
        int shiftDistance = 1;
        while (true) {
            indexToShift = (indexToShift - 1) & capacityMask;
            long keyToShift;
            if ((keyToShift = keys[indexToShift]) == free) {
                break;
            } 
            if ((((LHash.SeparateKVLongKeyMixing.mix(keyToShift)) - indexToShift) & capacityMask) >= shiftDistance) {
                keys[indexToRemove] = keyToShift;
                vals[indexToRemove] = vals[indexToShift];
                indexToRemove = indexToShift;
                shiftDistance = 1;
            } else {
                shiftDistance++;
                if (indexToShift == (1 + index)) {
                    throw new ConcurrentModificationException();
                } 
            }
        }
        keys[indexToRemove] = free;
        vals[indexToRemove] = null;
        postRemoveHook();
    }

    public boolean justRemove(long key) {
        long free;
        if (key != (free = freeValue)) {
            long[] keys = set;
            int capacityMask = (keys.length) - 1;
            int index;
            long cur;
            keyPresent : if ((cur = keys[(index = (LHash.SeparateKVLongKeyMixing.mix(key)) & capacityMask)]) != key) {
                if (cur == free) {
                    return false;
                } else {
                    while (true) {
                        if ((cur = keys[(index = (index - 1) & capacityMask)]) == key) {
                            break keyPresent;
                        } else if (cur == free) {
                            return false;
                        } 
                    }
                }
            } 
            T[] vals = values;
            int indexToRemove = index;
            int indexToShift = indexToRemove;
            int shiftDistance = 1;
            while (true) {
                indexToShift = (indexToShift - 1) & capacityMask;
                long keyToShift;
                if ((keyToShift = keys[indexToShift]) == free) {
                    break;
                } 
                if ((((LHash.SeparateKVLongKeyMixing.mix(keyToShift)) - indexToShift) & capacityMask) >= shiftDistance) {
                    keys[indexToRemove] = keyToShift;
                    vals[indexToRemove] = vals[indexToShift];
                    indexToRemove = indexToShift;
                    shiftDistance = 1;
                } else {
                    shiftDistance++;
                    if (indexToShift == (1 + index)) {
                        throw new ConcurrentModificationException();
                    } 
                }
            }
            keys[indexToRemove] = free;
            vals[indexToRemove] = null;
            postRemoveHook();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T remove(long key) {
        long free;
        if (key != (free = freeValue)) {
            long[] keys = set;
            int capacityMask = (keys.length) - 1;
            int index;
            long cur;
            keyPresent : if ((cur = keys[(index = (LHash.SeparateKVLongKeyMixing.mix(key)) & capacityMask)]) != key) {
                if (cur == free) {
                    return null;
                } else {
                    while (true) {
                        if ((cur = keys[(index = (index - 1) & capacityMask)]) == key) {
                            break keyPresent;
                        } else if (cur == free) {
                            return null;
                        } 
                    }
                }
            } 
            T[] vals = values;
            T val = vals[index];
            int indexToRemove = index;
            int indexToShift = indexToRemove;
            int shiftDistance = 1;
            while (true) {
                indexToShift = (indexToShift - 1) & capacityMask;
                long keyToShift;
                if ((keyToShift = keys[indexToShift]) == free) {
                    break;
                } 
                if ((((LHash.SeparateKVLongKeyMixing.mix(keyToShift)) - indexToShift) & capacityMask) >= shiftDistance) {
                    keys[indexToRemove] = keyToShift;
                    vals[indexToRemove] = vals[indexToShift];
                    indexToRemove = indexToShift;
                    shiftDistance = 1;
                } else {
                    shiftDistance++;
                    if (indexToShift == (1 + index)) {
                        throw new ConcurrentModificationException();
                    } 
                }
            }
            keys[indexToRemove] = free;
            vals[indexToRemove] = null;
            postRemoveHook();
            return val;
        } else {
            return null;
        }
    }

    public boolean removeIf(Predicate<? super Long> filter) {
        if (filter == null)
            throw new NullPointerException();
        
        if (KolobokeLongMap.this.isEmpty())
            return false;
        
        boolean changed = false;
        long free = freeValue;
        long[] keys = set;
        int capacityMask = (keys.length) - 1;
        int firstDelayedRemoved = -1;
        long delayedRemoved = 0L;
        T[] vals = values;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                if (filter.test(key)) {
                    closeDeletion : if (firstDelayedRemoved < 0) {
                        int indexToRemove = i;
                        int indexToShift = indexToRemove;
                        int shiftDistance = 1;
                        while (true) {
                            indexToShift = (indexToShift - 1) & capacityMask;
                            long keyToShift;
                            if ((keyToShift = keys[indexToShift]) == free) {
                                break;
                            } 
                            if ((((LHash.SeparateKVLongKeyMixing.mix(keyToShift)) - indexToShift) & capacityMask) >= shiftDistance) {
                                if (indexToShift > indexToRemove) {
                                    firstDelayedRemoved = i;
                                    delayedRemoved = key;
                                    keys[indexToRemove] = key;
                                    break closeDeletion;
                                } 
                                if (indexToRemove == i) {
                                    i++;
                                } 
                                keys[indexToRemove] = keyToShift;
                                vals[indexToRemove] = vals[indexToShift];
                                indexToRemove = indexToShift;
                                shiftDistance = 1;
                            } else {
                                shiftDistance++;
                                if (indexToShift == (1 + i)) {
                                    throw new ConcurrentModificationException();
                                } 
                            }
                        }
                        keys[indexToRemove] = free;
                        vals[indexToRemove] = null;
                        postRemoveHook();
                    } else {
                        keys[i] = delayedRemoved;
                    }
                    changed = true;
                } 
            } 
        }
        if (firstDelayedRemoved >= 0) {
            closeDelayedRemoved(firstDelayedRemoved, delayedRemoved);
        } 
        return changed;
    }

    public boolean removeIf(LongPredicate filter) {
        if (filter == null)
            throw new NullPointerException();
        
        if (KolobokeLongMap.this.isEmpty())
            return false;
        
        boolean changed = false;
        long free = freeValue;
        long[] keys = set;
        int capacityMask = (keys.length) - 1;
        int firstDelayedRemoved = -1;
        long delayedRemoved = 0L;
        T[] vals = values;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                if (filter.test(key)) {
                    closeDeletion : if (firstDelayedRemoved < 0) {
                        int indexToRemove = i;
                        int indexToShift = indexToRemove;
                        int shiftDistance = 1;
                        while (true) {
                            indexToShift = (indexToShift - 1) & capacityMask;
                            long keyToShift;
                            if ((keyToShift = keys[indexToShift]) == free) {
                                break;
                            } 
                            if ((((LHash.SeparateKVLongKeyMixing.mix(keyToShift)) - indexToShift) & capacityMask) >= shiftDistance) {
                                if (indexToShift > indexToRemove) {
                                    firstDelayedRemoved = i;
                                    delayedRemoved = key;
                                    keys[indexToRemove] = key;
                                    break closeDeletion;
                                } 
                                if (indexToRemove == i) {
                                    i++;
                                } 
                                keys[indexToRemove] = keyToShift;
                                vals[indexToRemove] = vals[indexToShift];
                                indexToRemove = indexToShift;
                                shiftDistance = 1;
                            } else {
                                shiftDistance++;
                                if (indexToShift == (1 + i)) {
                                    throw new ConcurrentModificationException();
                                } 
                            }
                        }
                        keys[indexToRemove] = free;
                        vals[indexToRemove] = null;
                        postRemoveHook();
                    } else {
                        keys[i] = delayedRemoved;
                    }
                    changed = true;
                } 
            } 
        }
        if (firstDelayedRemoved >= 0) {
            closeDelayedRemoved(firstDelayedRemoved, delayedRemoved);
        } 
        return changed;
    }

    @SuppressFBWarnings(value = "EC_UNRELATED_TYPES_USING_POINTER_EQUALITY")
    public boolean removeAll(@Nonnull
    HashLongSet thisC, @Nonnull
    Collection<?> c) {
        if (thisC == ((Object) (c)))
            throw new IllegalArgumentException();
        
        if ((KolobokeLongMap.this.isEmpty()) || (c.isEmpty()))
            return false;
        
        boolean changed = false;
        long free = freeValue;
        long[] keys = set;
        int capacityMask = (keys.length) - 1;
        int firstDelayedRemoved = -1;
        long delayedRemoved = 0L;
        T[] vals = values;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                if (c.contains(key)) {
                    closeDeletion : if (firstDelayedRemoved < 0) {
                        int indexToRemove = i;
                        int indexToShift = indexToRemove;
                        int shiftDistance = 1;
                        while (true) {
                            indexToShift = (indexToShift - 1) & capacityMask;
                            long keyToShift;
                            if ((keyToShift = keys[indexToShift]) == free) {
                                break;
                            } 
                            if ((((LHash.SeparateKVLongKeyMixing.mix(keyToShift)) - indexToShift) & capacityMask) >= shiftDistance) {
                                if (indexToShift > indexToRemove) {
                                    firstDelayedRemoved = i;
                                    delayedRemoved = key;
                                    keys[indexToRemove] = key;
                                    break closeDeletion;
                                } 
                                if (indexToRemove == i) {
                                    i++;
                                } 
                                keys[indexToRemove] = keyToShift;
                                vals[indexToRemove] = vals[indexToShift];
                                indexToRemove = indexToShift;
                                shiftDistance = 1;
                            } else {
                                shiftDistance++;
                                if (indexToShift == (1 + i)) {
                                    throw new ConcurrentModificationException();
                                } 
                            }
                        }
                        keys[indexToRemove] = free;
                        vals[indexToRemove] = null;
                        postRemoveHook();
                    } else {
                        keys[i] = delayedRemoved;
                    }
                    changed = true;
                } 
            } 
        }
        if (firstDelayedRemoved >= 0) {
            closeDelayedRemoved(firstDelayedRemoved, delayedRemoved);
        } 
        return changed;
    }

    boolean removeAll(@Nonnull
    HashLongSet thisC, @Nonnull
    LongCollection c) {
        if (thisC == ((Object) (c)))
            throw new IllegalArgumentException();
        
        if ((KolobokeLongMap.this.isEmpty()) || (c.isEmpty()))
            return false;
        
        boolean changed = false;
        long free = freeValue;
        long[] keys = set;
        int capacityMask = (keys.length) - 1;
        int firstDelayedRemoved = -1;
        long delayedRemoved = 0L;
        T[] vals = values;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                if (c.contains(key)) {
                    closeDeletion : if (firstDelayedRemoved < 0) {
                        int indexToRemove = i;
                        int indexToShift = indexToRemove;
                        int shiftDistance = 1;
                        while (true) {
                            indexToShift = (indexToShift - 1) & capacityMask;
                            long keyToShift;
                            if ((keyToShift = keys[indexToShift]) == free) {
                                break;
                            } 
                            if ((((LHash.SeparateKVLongKeyMixing.mix(keyToShift)) - indexToShift) & capacityMask) >= shiftDistance) {
                                if (indexToShift > indexToRemove) {
                                    firstDelayedRemoved = i;
                                    delayedRemoved = key;
                                    keys[indexToRemove] = key;
                                    break closeDeletion;
                                } 
                                if (indexToRemove == i) {
                                    i++;
                                } 
                                keys[indexToRemove] = keyToShift;
                                vals[indexToRemove] = vals[indexToShift];
                                indexToRemove = indexToShift;
                                shiftDistance = 1;
                            } else {
                                shiftDistance++;
                                if (indexToShift == (1 + i)) {
                                    throw new ConcurrentModificationException();
                                } 
                            }
                        }
                        keys[indexToRemove] = free;
                        vals[indexToRemove] = null;
                        postRemoveHook();
                    } else {
                        keys[i] = delayedRemoved;
                    }
                    changed = true;
                } 
            } 
        }
        if (firstDelayedRemoved >= 0) {
            closeDelayedRemoved(firstDelayedRemoved, delayedRemoved);
        } 
        return changed;
    }

    @SuppressFBWarnings(value = "EC_UNRELATED_TYPES_USING_POINTER_EQUALITY")
    public boolean retainAll(@Nonnull
    HashLongSet thisC, @Nonnull
    Collection<?> c) {
        if (c instanceof LongCollection)
            return retainAll(thisC, ((LongCollection) (c)));
        
        if (thisC == ((Object) (c)))
            throw new IllegalArgumentException();
        
        if (KolobokeLongMap.this.isEmpty())
            return false;
        
        if (c.isEmpty()) {
            clear();
            return true;
        } 
        boolean changed = false;
        long free = freeValue;
        long[] keys = set;
        int capacityMask = (keys.length) - 1;
        int firstDelayedRemoved = -1;
        long delayedRemoved = 0L;
        T[] vals = values;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                if (!(c.contains(key))) {
                    closeDeletion : if (firstDelayedRemoved < 0) {
                        int indexToRemove = i;
                        int indexToShift = indexToRemove;
                        int shiftDistance = 1;
                        while (true) {
                            indexToShift = (indexToShift - 1) & capacityMask;
                            long keyToShift;
                            if ((keyToShift = keys[indexToShift]) == free) {
                                break;
                            } 
                            if ((((LHash.SeparateKVLongKeyMixing.mix(keyToShift)) - indexToShift) & capacityMask) >= shiftDistance) {
                                if (indexToShift > indexToRemove) {
                                    firstDelayedRemoved = i;
                                    delayedRemoved = key;
                                    keys[indexToRemove] = key;
                                    break closeDeletion;
                                } 
                                if (indexToRemove == i) {
                                    i++;
                                } 
                                keys[indexToRemove] = keyToShift;
                                vals[indexToRemove] = vals[indexToShift];
                                indexToRemove = indexToShift;
                                shiftDistance = 1;
                            } else {
                                shiftDistance++;
                                if (indexToShift == (1 + i)) {
                                    throw new ConcurrentModificationException();
                                } 
                            }
                        }
                        keys[indexToRemove] = free;
                        vals[indexToRemove] = null;
                        postRemoveHook();
                    } else {
                        keys[i] = delayedRemoved;
                    }
                    changed = true;
                } 
            } 
        }
        if (firstDelayedRemoved >= 0) {
            closeDelayedRemoved(firstDelayedRemoved, delayedRemoved);
        } 
        return changed;
    }

    private boolean retainAll(@Nonnull
    HashLongSet thisC, @Nonnull
    LongCollection c) {
        if (thisC == ((Object) (c)))
            throw new IllegalArgumentException();
        
        if (KolobokeLongMap.this.isEmpty())
            return false;
        
        if (c.isEmpty()) {
            clear();
            return true;
        } 
        boolean changed = false;
        long free = freeValue;
        long[] keys = set;
        int capacityMask = (keys.length) - 1;
        int firstDelayedRemoved = -1;
        long delayedRemoved = 0L;
        T[] vals = values;
        for (int i = (keys.length) - 1; i >= 0; i--) {
            long key;
            if ((key = keys[i]) != free) {
                if (!(c.contains(key))) {
                    closeDeletion : if (firstDelayedRemoved < 0) {
                        int indexToRemove = i;
                        int indexToShift = indexToRemove;
                        int shiftDistance = 1;
                        while (true) {
                            indexToShift = (indexToShift - 1) & capacityMask;
                            long keyToShift;
                            if ((keyToShift = keys[indexToShift]) == free) {
                                break;
                            } 
                            if ((((LHash.SeparateKVLongKeyMixing.mix(keyToShift)) - indexToShift) & capacityMask) >= shiftDistance) {
                                if (indexToShift > indexToRemove) {
                                    firstDelayedRemoved = i;
                                    delayedRemoved = key;
                                    keys[indexToRemove] = key;
                                    break closeDeletion;
                                } 
                                if (indexToRemove == i) {
                                    i++;
                                } 
                                keys[indexToRemove] = keyToShift;
                                vals[indexToRemove] = vals[indexToShift];
                                indexToRemove = indexToShift;
                                shiftDistance = 1;
                            } else {
                                shiftDistance++;
                                if (indexToShift == (1 + i)) {
                                    throw new ConcurrentModificationException();
                                } 
                            }
                        }
                        keys[indexToRemove] = free;
                        vals[indexToRemove] = null;
                        postRemoveHook();
                    } else {
                        keys[i] = delayedRemoved;
                    }
                    changed = true;
                } 
            } 
        }
        if (firstDelayedRemoved >= 0) {
            closeDelayedRemoved(firstDelayedRemoved, delayedRemoved);
        } 
        return changed;
    }

    void closeDelayedRemoved(int firstDelayedRemoved, long delayedRemoved) {
        long free = freeValue;
        long[] keys = set;
        T[] vals = values;
        int capacityMask = (keys.length) - 1;
        for (int i = firstDelayedRemoved; i >= 0; i--) {
            if ((keys[i]) == delayedRemoved) {
                int indexToRemove = i;
                int indexToShift = indexToRemove;
                int shiftDistance = 1;
                while (true) {
                    indexToShift = (indexToShift - 1) & capacityMask;
                    long keyToShift;
                    if ((keyToShift = keys[indexToShift]) == free) {
                        break;
                    } 
                    if ((keyToShift != delayedRemoved) && ((((LHash.SeparateKVLongKeyMixing.mix(keyToShift)) - indexToShift) & capacityMask) >= shiftDistance)) {
                        keys[indexToRemove] = keyToShift;
                        vals[indexToRemove] = vals[indexToShift];
                        indexToRemove = indexToShift;
                        shiftDistance = 1;
                    } else {
                        shiftDistance++;
                        if (indexToShift == (1 + i)) {
                            throw new ConcurrentModificationException();
                        } 
                    }
                }
                keys[indexToRemove] = free;
                vals[indexToRemove] = null;
                postRemoveHook();
            } 
        }
    }

    public LongIterator iterator() {
        return new NoRemovedKeyIterator();
    }

    public LongCursor setCursor() {
        return new NoRemovedKeyCursor();
    }

    class NoRemovedKeyIterator extends KolobokeLongMap.NoRemovedIterator {
        T[] vals;

        private NoRemovedKeyIterator() {
            super();
            vals = values;
        }

        @Override
        public void remove() {
            int index;
            if ((index = KolobokeLongMap.NoRemovedKeyIterator.this.index) >= 0) {
                KolobokeLongMap.NoRemovedKeyIterator.this.index = -1;
                long[] keys = KolobokeLongMap.NoRemovedKeyIterator.this.keys;
                T[] vals = KolobokeLongMap.NoRemovedKeyIterator.this.vals;
                if (keys == (set)) {
                    int capacityMask = KolobokeLongMap.NoRemovedKeyIterator.this.capacityMask;
                    int indexToRemove = index;
                    int indexToShift = indexToRemove;
                    int shiftDistance = 1;
                    while (true) {
                        indexToShift = (indexToShift - 1) & capacityMask;
                        long keyToShift;
                        if ((keyToShift = keys[indexToShift]) == (free)) {
                            break;
                        } 
                        if ((((LHash.SeparateKVLongKeyMixing.mix(keyToShift)) - indexToShift) & capacityMask) >= shiftDistance) {
                            if ((KolobokeLongMap.NoRemovedKeyIterator.this.keys) == keys) {
                                if (indexToShift > indexToRemove) {
                                    int slotsToCopy;
                                    if ((slotsToCopy = (nextIndex) + 1) > 0) {
                                        KolobokeLongMap.NoRemovedKeyIterator.this.keys = Arrays.copyOf(keys, slotsToCopy);
                                        KolobokeLongMap.NoRemovedKeyIterator.this.vals = Arrays.copyOf(vals, slotsToCopy);
                                        if (indexToRemove < slotsToCopy) {
                                            KolobokeLongMap.NoRemovedKeyIterator.this.keys[indexToRemove] = free;
                                            KolobokeLongMap.NoRemovedKeyIterator.this.vals[indexToRemove] = null;
                                        } 
                                    } 
                                } else if (indexToRemove == index) {
                                    KolobokeLongMap.NoRemovedKeyIterator.this.nextIndex = index;
                                    if (indexToShift < (index - 1)) {
                                        KolobokeLongMap.NoRemovedKeyIterator.this.next = keyToShift;
                                    } 
                                } 
                            } 
                            keys[indexToRemove] = keyToShift;
                            vals[indexToRemove] = vals[indexToShift];
                            indexToRemove = indexToShift;
                            shiftDistance = 1;
                        } else {
                            shiftDistance++;
                            if (indexToShift == (1 + index)) {
                                throw new ConcurrentModificationException();
                            } 
                        }
                    }
                    keys[indexToRemove] = free;
                    vals[indexToRemove] = null;
                    postRemoveHook();
                } else {
                    justRemove(keys[index]);
                    vals[index] = null;
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    class NoRemovedKeyCursor extends KolobokeLongMap.NoRemovedCursor {
        T[] vals;

        private NoRemovedKeyCursor() {
            super();
            vals = values;
        }

        @Override
        public void remove() {
            long curKey;
            long free;
            if ((curKey = KolobokeLongMap.NoRemovedKeyCursor.this.curKey) != (free = KolobokeLongMap.NoRemovedKeyCursor.this.free)) {
                KolobokeLongMap.NoRemovedKeyCursor.this.curKey = free;
                int index = KolobokeLongMap.NoRemovedKeyCursor.this.index;
                long[] keys = KolobokeLongMap.NoRemovedKeyCursor.this.keys;
                T[] vals = KolobokeLongMap.NoRemovedKeyCursor.this.vals;
                if (keys == (set)) {
                    int capacityMask = KolobokeLongMap.NoRemovedKeyCursor.this.capacityMask;
                    int indexToRemove = index;
                    int indexToShift = indexToRemove;
                    int shiftDistance = 1;
                    while (true) {
                        indexToShift = (indexToShift - 1) & capacityMask;
                        long keyToShift;
                        if ((keyToShift = keys[indexToShift]) == free) {
                            break;
                        } 
                        if ((((LHash.SeparateKVLongKeyMixing.mix(keyToShift)) - indexToShift) & capacityMask) >= shiftDistance) {
                            if ((KolobokeLongMap.NoRemovedKeyCursor.this.keys) == keys) {
                                if (indexToShift > indexToRemove) {
                                    int slotsToCopy;
                                    if ((slotsToCopy = index) > 0) {
                                        KolobokeLongMap.NoRemovedKeyCursor.this.keys = Arrays.copyOf(keys, slotsToCopy);
                                        KolobokeLongMap.NoRemovedKeyCursor.this.vals = Arrays.copyOf(vals, slotsToCopy);
                                        if (indexToRemove < slotsToCopy) {
                                            KolobokeLongMap.NoRemovedKeyCursor.this.keys[indexToRemove] = free;
                                            KolobokeLongMap.NoRemovedKeyCursor.this.vals[indexToRemove] = null;
                                        } 
                                    } 
                                } else if (indexToRemove == index) {
                                    KolobokeLongMap.NoRemovedKeyCursor.this.index = ++index;
                                } 
                            } 
                            keys[indexToRemove] = keyToShift;
                            vals[indexToRemove] = vals[indexToShift];
                            indexToRemove = indexToShift;
                            shiftDistance = 1;
                        } else {
                            shiftDistance++;
                            if (indexToShift == (1 + index)) {
                                throw new ConcurrentModificationException();
                            } 
                        }
                    }
                    keys[indexToRemove] = free;
                    vals[indexToRemove] = null;
                    postRemoveHook();
                } else {
                    justRemove(curKey);
                    vals[index] = null;
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    class ValueView extends AbstractObjValueView<T> {
        @Override
        public Equivalence<T> equivalence() {
            return valueEquivalence();
        }

        @Override
        public int size() {
            return KolobokeLongMap.this.size();
        }

        @Override
        public boolean shrink() {
            return KolobokeLongMap.this.shrink();
        }

        @Override
        public boolean contains(Object o) {
            return KolobokeLongMap.this.containsValue(o);
        }

        @Override
        public void forEach(Consumer<? super T> action) {
            if (action == null)
                throw new NullPointerException();
            
            if (KolobokeLongMap.ValueView.this.isEmpty())
                return ;
            
            long free = freeValue;
            long[] keys = set;
            T[] vals = values;
            for (int i = (keys.length) - 1; i >= 0; i--) {
                if ((keys[i]) != free) {
                    action.accept(vals[i]);
                } 
            }
        }

        @Override
        public boolean forEachWhile(Predicate<? super T> predicate) {
            if (predicate == null)
                throw new NullPointerException();
            
            if (KolobokeLongMap.ValueView.this.isEmpty())
                return true;
            
            boolean terminated = false;
            long free = freeValue;
            long[] keys = set;
            T[] vals = values;
            for (int i = (keys.length) - 1; i >= 0; i--) {
                if ((keys[i]) != free) {
                    if (!(predicate.test(vals[i]))) {
                        terminated = true;
                        break;
                    } 
                } 
            }
            return !terminated;
        }

        @Override
        public boolean allContainingIn(ObjCollection<?> c) {
            if (KolobokeLongMap.ValueView.this.isEmpty())
                return true;
            
            boolean containsAll = true;
            long free = freeValue;
            long[] keys = set;
            T[] vals = values;
            for (int i = (keys.length) - 1; i >= 0; i--) {
                if ((keys[i]) != free) {
                    if (!(c.contains(vals[i]))) {
                        containsAll = false;
                        break;
                    } 
                } 
            }
            return containsAll;
        }

        @Override
        public boolean reverseAddAllTo(ObjCollection<? super T> c) {
            if (KolobokeLongMap.ValueView.this.isEmpty())
                return false;
            
            boolean changed = false;
            long free = freeValue;
            long[] keys = set;
            T[] vals = values;
            for (int i = (keys.length) - 1; i >= 0; i--) {
                if ((keys[i]) != free) {
                    changed |= c.add(vals[i]);
                } 
            }
            return changed;
        }

        @Override
        public boolean reverseRemoveAllFrom(ObjSet<?> s) {
            if ((KolobokeLongMap.ValueView.this.isEmpty()) || (s.isEmpty()))
                return false;
            
            boolean changed = false;
            long free = freeValue;
            long[] keys = set;
            T[] vals = values;
            for (int i = (keys.length) - 1; i >= 0; i--) {
                if ((keys[i]) != free) {
                    changed |= s.remove(vals[i]);
                } 
            }
            return changed;
        }

        @Override
        @Nonnull
        public ObjIterator<T> iterator() {
            return new NoRemovedValueIterator();
        }

        @Nonnull
        @Override
        public ObjCursor<T> cursor() {
            return new NoRemovedValueCursor();
        }

        @Override
        @Nonnull
        public Object[] toArray() {
            int size = size();
            Object[] result = new Object[size];
            if (size == 0)
                return result;
            
            int resultIndex = 0;
            long free = freeValue;
            long[] keys = set;
            T[] vals = values;
            for (int i = (keys.length) - 1; i >= 0; i--) {
                if ((keys[i]) != free) {
                    result[(resultIndex++)] = vals[i];
                } 
            }
            return result;
        }

        @Override
        @SuppressWarnings(value = "unchecked")
        @Nonnull
        public <T2>  T2[] toArray(@Nonnull
        T2[] a) {
            int size = size();
            if ((a.length) < size) {
                Class<?> elementType = a.getClass().getComponentType();
                a = ((T2[]) (Array.newInstance(elementType, size)));
            } 
            if (size == 0) {
                if ((a.length) > 0)
                    a[0] = null;
                
                return a;
            } 
            int resultIndex = 0;
            long free = freeValue;
            long[] keys = set;
            T[] vals = values;
            for (int i = (keys.length) - 1; i >= 0; i--) {
                if ((keys[i]) != free) {
                    a[(resultIndex++)] = ((T2) (vals[i]));
                } 
            }
            if ((a.length) > resultIndex)
                a[resultIndex] = null;
            
            return a;
        }

        @Override
        public String toString() {
            if (KolobokeLongMap.ValueView.this.isEmpty())
                return "[]";
            
            StringBuilder sb = new StringBuilder();
            int elementCount = 0;
            long free = freeValue;
            long[] keys = set;
            T[] vals = values;
            for (int i = (keys.length) - 1; i >= 0; i--) {
                if ((keys[i]) != free) {
                    T val;
                    sb.append(' ').append(((val = vals[i]) != ((Object) (KolobokeLongMap.ValueView.this)) ? val : "(this Collection)")).append(',');
                    if ((++elementCount) == 8) {
                        int expectedLength = (sb.length()) * ((size()) / 8);
                        sb.ensureCapacity((expectedLength + (expectedLength / 2)));
                    } 
                } 
            }
            sb.setCharAt(0, '[');
            sb.setCharAt(((sb.length()) - 1), ']');
            return sb.toString();
        }

        @Override
        public boolean remove(Object o) {
            return removeValue(o);
        }

        @Override
        public void clear() {
            KolobokeLongMap.this.clear();
        }

        @Override
        public boolean removeIf(Predicate<? super T> filter) {
            if (filter == null)
                throw new NullPointerException();
            
            if (KolobokeLongMap.ValueView.this.isEmpty())
                return false;
            
            boolean changed = false;
            long free = freeValue;
            long[] keys = set;
            int capacityMask = (keys.length) - 1;
            int firstDelayedRemoved = -1;
            long delayedRemoved = 0L;
            T[] vals = values;
            for (int i = (keys.length) - 1; i >= 0; i--) {
                long key;
                if ((key = keys[i]) != free) {
                    if (filter.test(vals[i])) {
                        closeDeletion : if (firstDelayedRemoved < 0) {
                            int indexToRemove = i;
                            int indexToShift = indexToRemove;
                            int shiftDistance = 1;
                            while (true) {
                                indexToShift = (indexToShift - 1) & capacityMask;
                                long keyToShift;
                                if ((keyToShift = keys[indexToShift]) == free) {
                                    break;
                                } 
                                if ((((LHash.SeparateKVLongKeyMixing.mix(keyToShift)) - indexToShift) & capacityMask) >= shiftDistance) {
                                    if (indexToShift > indexToRemove) {
                                        firstDelayedRemoved = i;
                                        delayedRemoved = key;
                                        keys[indexToRemove] = key;
                                        break closeDeletion;
                                    } 
                                    if (indexToRemove == i) {
                                        i++;
                                    } 
                                    keys[indexToRemove] = keyToShift;
                                    vals[indexToRemove] = vals[indexToShift];
                                    indexToRemove = indexToShift;
                                    shiftDistance = 1;
                                } else {
                                    shiftDistance++;
                                    if (indexToShift == (1 + i)) {
                                        throw new ConcurrentModificationException();
                                    } 
                                }
                            }
                            keys[indexToRemove] = free;
                            vals[indexToRemove] = null;
                            postRemoveHook();
                        } else {
                            keys[i] = delayedRemoved;
                        }
                        changed = true;
                    } 
                } 
            }
            if (firstDelayedRemoved >= 0) {
                closeDelayedRemoved(firstDelayedRemoved, delayedRemoved);
            } 
            return changed;
        }

        @Override
        public boolean removeAll(@Nonnull
        Collection<?> c) {
            if ((KolobokeLongMap.ValueView.this) == ((Object) (c)))
                throw new IllegalArgumentException();
            
            if ((KolobokeLongMap.ValueView.this.isEmpty()) || (c.isEmpty()))
                return false;
            
            boolean changed = false;
            long free = freeValue;
            long[] keys = set;
            int capacityMask = (keys.length) - 1;
            int firstDelayedRemoved = -1;
            long delayedRemoved = 0L;
            T[] vals = values;
            for (int i = (keys.length) - 1; i >= 0; i--) {
                long key;
                if ((key = keys[i]) != free) {
                    if (c.contains(vals[i])) {
                        closeDeletion : if (firstDelayedRemoved < 0) {
                            int indexToRemove = i;
                            int indexToShift = indexToRemove;
                            int shiftDistance = 1;
                            while (true) {
                                indexToShift = (indexToShift - 1) & capacityMask;
                                long keyToShift;
                                if ((keyToShift = keys[indexToShift]) == free) {
                                    break;
                                } 
                                if ((((LHash.SeparateKVLongKeyMixing.mix(keyToShift)) - indexToShift) & capacityMask) >= shiftDistance) {
                                    if (indexToShift > indexToRemove) {
                                        firstDelayedRemoved = i;
                                        delayedRemoved = key;
                                        keys[indexToRemove] = key;
                                        break closeDeletion;
                                    } 
                                    if (indexToRemove == i) {
                                        i++;
                                    } 
                                    keys[indexToRemove] = keyToShift;
                                    vals[indexToRemove] = vals[indexToShift];
                                    indexToRemove = indexToShift;
                                    shiftDistance = 1;
                                } else {
                                    shiftDistance++;
                                    if (indexToShift == (1 + i)) {
                                        throw new ConcurrentModificationException();
                                    } 
                                }
                            }
                            keys[indexToRemove] = free;
                            vals[indexToRemove] = null;
                            postRemoveHook();
                        } else {
                            keys[i] = delayedRemoved;
                        }
                        changed = true;
                    } 
                } 
            }
            if (firstDelayedRemoved >= 0) {
                closeDelayedRemoved(firstDelayedRemoved, delayedRemoved);
            } 
            return changed;
        }

        @Override
        public boolean retainAll(@Nonnull
        Collection<?> c) {
            if ((KolobokeLongMap.ValueView.this) == ((Object) (c)))
                throw new IllegalArgumentException();
            
            if (KolobokeLongMap.ValueView.this.isEmpty())
                return false;
            
            if (c.isEmpty()) {
                clear();
                return true;
            } 
            boolean changed = false;
            long free = freeValue;
            long[] keys = set;
            int capacityMask = (keys.length) - 1;
            int firstDelayedRemoved = -1;
            long delayedRemoved = 0L;
            T[] vals = values;
            for (int i = (keys.length) - 1; i >= 0; i--) {
                long key;
                if ((key = keys[i]) != free) {
                    if (!(c.contains(vals[i]))) {
                        closeDeletion : if (firstDelayedRemoved < 0) {
                            int indexToRemove = i;
                            int indexToShift = indexToRemove;
                            int shiftDistance = 1;
                            while (true) {
                                indexToShift = (indexToShift - 1) & capacityMask;
                                long keyToShift;
                                if ((keyToShift = keys[indexToShift]) == free) {
                                    break;
                                } 
                                if ((((LHash.SeparateKVLongKeyMixing.mix(keyToShift)) - indexToShift) & capacityMask) >= shiftDistance) {
                                    if (indexToShift > indexToRemove) {
                                        firstDelayedRemoved = i;
                                        delayedRemoved = key;
                                        keys[indexToRemove] = key;
                                        break closeDeletion;
                                    } 
                                    if (indexToRemove == i) {
                                        i++;
                                    } 
                                    keys[indexToRemove] = keyToShift;
                                    vals[indexToRemove] = vals[indexToShift];
                                    indexToRemove = indexToShift;
                                    shiftDistance = 1;
                                } else {
                                    shiftDistance++;
                                    if (indexToShift == (1 + i)) {
                                        throw new ConcurrentModificationException();
                                    } 
                                }
                            }
                            keys[indexToRemove] = free;
                            vals[indexToRemove] = null;
                            postRemoveHook();
                        } else {
                            keys[i] = delayedRemoved;
                        }
                        changed = true;
                    } 
                } 
            }
            if (firstDelayedRemoved >= 0) {
                closeDelayedRemoved(firstDelayedRemoved, delayedRemoved);
            } 
            return changed;
        }
    }

    class NoRemovedValueIterator implements ObjIterator<T> {
        long[] keys;

        T[] vals;

        final long free;

        final int capacityMask;

        int index = -1;

        int nextIndex;

        T next;

        NoRemovedValueIterator() {
            long[] keys = KolobokeLongMap.NoRemovedValueIterator.this.keys = set;
            capacityMask = (keys.length) - 1;
            T[] vals = KolobokeLongMap.NoRemovedValueIterator.this.vals = values;
            long free = this.free = freeValue;
            int nextI = keys.length;
            while ((--nextI) >= 0) {
                if ((keys[nextI]) != free) {
                    next = vals[nextI];
                    break;
                } 
            }
            nextIndex = nextI;
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            if (action == null)
                throw new NullPointerException();
            
            long[] keys = KolobokeLongMap.NoRemovedValueIterator.this.keys;
            T[] vals = KolobokeLongMap.NoRemovedValueIterator.this.vals;
            long free = KolobokeLongMap.NoRemovedValueIterator.this.free;
            int nextI = nextIndex;
            for (int i = nextI; i >= 0; i--) {
                if ((keys[i]) != free) {
                    action.accept(vals[i]);
                } 
            }
            if (nextI != (nextIndex)) {
                throw new ConcurrentModificationException();
            } 
            index = nextIndex = -1;
        }

        @Override
        public boolean hasNext() {
            return (nextIndex) >= 0;
        }

        @Override
        public T next() {
            int nextI;
            if ((nextI = nextIndex) >= 0) {
                index = nextI;
                long[] keys = KolobokeLongMap.NoRemovedValueIterator.this.keys;
                long free = KolobokeLongMap.NoRemovedValueIterator.this.free;
                T prev = next;
                while ((--nextI) >= 0) {
                    if ((keys[nextI]) != free) {
                        next = vals[nextI];
                        break;
                    } 
                }
                nextIndex = nextI;
                return prev;
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            int index;
            if ((index = KolobokeLongMap.NoRemovedValueIterator.this.index) >= 0) {
                KolobokeLongMap.NoRemovedValueIterator.this.index = -1;
                long[] keys = KolobokeLongMap.NoRemovedValueIterator.this.keys;
                T[] vals = KolobokeLongMap.NoRemovedValueIterator.this.vals;
                if (keys == (set)) {
                    int capacityMask = KolobokeLongMap.NoRemovedValueIterator.this.capacityMask;
                    int indexToRemove = index;
                    int indexToShift = indexToRemove;
                    int shiftDistance = 1;
                    while (true) {
                        indexToShift = (indexToShift - 1) & capacityMask;
                        long keyToShift;
                        if ((keyToShift = keys[indexToShift]) == (free)) {
                            break;
                        } 
                        if ((((LHash.SeparateKVLongKeyMixing.mix(keyToShift)) - indexToShift) & capacityMask) >= shiftDistance) {
                            if ((KolobokeLongMap.NoRemovedValueIterator.this.keys) == keys) {
                                if (indexToShift > indexToRemove) {
                                    int slotsToCopy;
                                    if ((slotsToCopy = (nextIndex) + 1) > 0) {
                                        KolobokeLongMap.NoRemovedValueIterator.this.keys = Arrays.copyOf(keys, slotsToCopy);
                                        KolobokeLongMap.NoRemovedValueIterator.this.vals = Arrays.copyOf(vals, slotsToCopy);
                                        if (indexToRemove < slotsToCopy) {
                                            KolobokeLongMap.NoRemovedValueIterator.this.keys[indexToRemove] = free;
                                            KolobokeLongMap.NoRemovedValueIterator.this.vals[indexToRemove] = null;
                                        } 
                                    } 
                                } else if (indexToRemove == index) {
                                    KolobokeLongMap.NoRemovedValueIterator.this.nextIndex = index;
                                    if (indexToShift < (index - 1)) {
                                        KolobokeLongMap.NoRemovedValueIterator.this.next = vals[indexToShift];
                                    } 
                                } 
                            } 
                            keys[indexToRemove] = keyToShift;
                            vals[indexToRemove] = vals[indexToShift];
                            indexToRemove = indexToShift;
                            shiftDistance = 1;
                        } else {
                            shiftDistance++;
                            if (indexToShift == (1 + index)) {
                                throw new ConcurrentModificationException();
                            } 
                        }
                    }
                    keys[indexToRemove] = free;
                    vals[indexToRemove] = null;
                    postRemoveHook();
                } else {
                    justRemove(keys[index]);
                    vals[index] = null;
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    class NoRemovedValueCursor implements ObjCursor<T> {
        long[] keys;

        T[] vals;

        final long free;

        final int capacityMask;

        int index;

        long curKey;

        T curValue;

        NoRemovedValueCursor() {
            long[] keys = KolobokeLongMap.NoRemovedValueCursor.this.keys = set;
            capacityMask = (keys.length) - 1;
            index = keys.length;
            vals = values;
            long free = this.free = freeValue;
            curKey = free;
        }

        @Override
        public void forEachForward(Consumer<? super T> action) {
            if (action == null)
                throw new NullPointerException();
            
            long[] keys = KolobokeLongMap.NoRemovedValueCursor.this.keys;
            T[] vals = KolobokeLongMap.NoRemovedValueCursor.this.vals;
            long free = KolobokeLongMap.NoRemovedValueCursor.this.free;
            int index = KolobokeLongMap.NoRemovedValueCursor.this.index;
            for (int i = index - 1; i >= 0; i--) {
                if ((keys[i]) != free) {
                    action.accept(vals[i]);
                } 
            }
            if (index != (KolobokeLongMap.NoRemovedValueCursor.this.index)) {
                throw new ConcurrentModificationException();
            } 
            KolobokeLongMap.NoRemovedValueCursor.this.index = -1;
            curKey = free;
        }

        @Override
        public T elem() {
            if ((curKey) != (free)) {
                return curValue;
            } else {
                throw new IllegalStateException();
            }
        }

        @Override
        public boolean moveNext() {
            long[] keys = KolobokeLongMap.NoRemovedValueCursor.this.keys;
            long free = KolobokeLongMap.NoRemovedValueCursor.this.free;
            for (int i = (index) - 1; i >= 0; i--) {
                long key;
                if ((key = keys[i]) != free) {
                    index = i;
                    curKey = key;
                    curValue = vals[i];
                    return true;
                } 
            }
            curKey = free;
            index = -1;
            return false;
        }

        @Override
        public void remove() {
            long curKey;
            long free;
            if ((curKey = KolobokeLongMap.NoRemovedValueCursor.this.curKey) != (free = KolobokeLongMap.NoRemovedValueCursor.this.free)) {
                KolobokeLongMap.NoRemovedValueCursor.this.curKey = free;
                int index = KolobokeLongMap.NoRemovedValueCursor.this.index;
                long[] keys = KolobokeLongMap.NoRemovedValueCursor.this.keys;
                T[] vals = KolobokeLongMap.NoRemovedValueCursor.this.vals;
                if (keys == (set)) {
                    int capacityMask = KolobokeLongMap.NoRemovedValueCursor.this.capacityMask;
                    int indexToRemove = index;
                    int indexToShift = indexToRemove;
                    int shiftDistance = 1;
                    while (true) {
                        indexToShift = (indexToShift - 1) & capacityMask;
                        long keyToShift;
                        if ((keyToShift = keys[indexToShift]) == free) {
                            break;
                        } 
                        if ((((LHash.SeparateKVLongKeyMixing.mix(keyToShift)) - indexToShift) & capacityMask) >= shiftDistance) {
                            if ((KolobokeLongMap.NoRemovedValueCursor.this.keys) == keys) {
                                if (indexToShift > indexToRemove) {
                                    int slotsToCopy;
                                    if ((slotsToCopy = index) > 0) {
                                        KolobokeLongMap.NoRemovedValueCursor.this.keys = Arrays.copyOf(keys, slotsToCopy);
                                        KolobokeLongMap.NoRemovedValueCursor.this.vals = Arrays.copyOf(vals, slotsToCopy);
                                        if (indexToRemove < slotsToCopy) {
                                            KolobokeLongMap.NoRemovedValueCursor.this.keys[indexToRemove] = free;
                                            KolobokeLongMap.NoRemovedValueCursor.this.vals[indexToRemove] = null;
                                        } 
                                    } 
                                } else if (indexToRemove == index) {
                                    KolobokeLongMap.NoRemovedValueCursor.this.index = ++index;
                                } 
                            } 
                            keys[indexToRemove] = keyToShift;
                            vals[indexToRemove] = vals[indexToShift];
                            indexToRemove = indexToShift;
                            shiftDistance = 1;
                        } else {
                            shiftDistance++;
                            if (indexToShift == (1 + index)) {
                                throw new ConcurrentModificationException();
                            } 
                        }
                    }
                    keys[indexToRemove] = free;
                    vals[indexToRemove] = null;
                    postRemoveHook();
                } else {
                    justRemove(curKey);
                    vals[index] = null;
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    KolobokeLongMap(HashConfig hashConfig, int expectedSize) {
        this.init(new HashConfigWrapper(hashConfig), expectedSize);
    }

    static class Support {
        static interface LongHash extends Hash {
            long freeValue();

            boolean supportRemoved();

            long removedValue();
        }

        static interface SeparateKVLongLHash extends LHash , KolobokeLongMap.Support.SeparateKVLongHash {        }

        static interface SeparateKVLongHash extends KolobokeLongMap.Support.LongHash {
            @Nonnull
            long[] keys();
        }
    }

    static final HashConfigWrapper DEFAULT_CONFIG_WRAPPER = new HashConfigWrapper(HashConfig.getDefault());
}