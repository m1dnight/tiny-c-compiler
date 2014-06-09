package Utils;

import java.util.ArrayList;

/**
 * Created by christophe on 09.06.14.
 */
public class MyBiMap<K, V>
{
    private ArrayList<Entry<K, V>> store = new ArrayList<Entry<K, V>>();

    class Entry<K, V>
    {
        Entry(K key, V value)
        {
            this.key = key;
            this.value = value;
        }

        public K key;
        public V value;

        public String toString()
        {
            return String.format("%s -> %s", key.toString(), value.toString());
        }
    }

    public MyBiMap()
    {
    }

    public void put(K key, V value)
    {
        for(Entry<K, V> e : store)
        {
            if(e.key.equals(key))
            {
                e.value = value;
                return;
            }
            if(e.value.equals(value))
            {
                e.key = key;
                return;
            }
        }
        store.add(new Entry<K, V>(key, value));
    }
    public boolean containsKey(K key)
    {
        for(Entry<K, V> e : store)
        {
            if(e.key.equals(key))
                return true;
        }
        return false;
    }

    public V getValue(K key)
    {
        for(Entry<K, V> e : store)
        {
            if(e.key.equals(key))
                return e.value;
        }
        return null;
    }

    public K getKey(V value)
    {
        for(Entry<K, V> e : store)
        {
            if(e.value.equals(value))
                return e.key;
        }
        return null;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Entry<K, V> e : store)
        {
            sb.append(e.toString());
            sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }

    }
