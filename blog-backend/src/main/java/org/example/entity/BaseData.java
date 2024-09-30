package org.example.entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 定义一个基础数据接口，包含将实体对象转换为视图对象的方法
 */
public interface BaseData {

    /**
     * 将当前对象转换为指定类型的视图对象，并对视图对象执行自定义操作
     *
     * @param clazz  视图对象的类
     * @param consumer 对视图对象执行的操作
     * @return 转换后的视图对象
     */
    default <V> V asViewObject(Class<V> clazz, Consumer<V> consumer){
        V v = this.asViewObject(clazz);
        consumer.accept(v);
        return v;
    }

    /**
     * 将当前对象转换为指定类型的视图对象
     *
     * @param clazz 视图对象的类
     * @return 转换后的视图对象
     */
    default <V> V asViewObject(Class<V> clazz){
        try{
            // 获取指定类的所有声明字段
            Field[] declaredFields = clazz.getDeclaredFields();
            // 获取指定类的构造函数
            Constructor<V> constructor = clazz.getConstructor();
            // 创建一个新的视图对象实例
            V v = constructor.newInstance();
            // 遍历所有声明字段，尝试复制值
            for (Field declaredField : declaredFields)
                convert(declaredField,v);
            return v;
        } catch (ReflectiveOperationException e) {
            // 捕获反射操作异常，抛出运行时异常
            throw new RuntimeException(new Exception().getMessage());
        }
    }

    /**
     * 复制字段值到视图对象
     *
     * @param field 目标字段
     * @param vo 视图对象
     */
    private void convert(Field field,Object vo){
        try{
            // 获取当前对象中对应字段的声明
            Field source = this.getClass().getDeclaredField(field.getName());
            // 设置字段可访问
            field.setAccessible(true);
            source.setAccessible(true);
            // 将当前对象的字段值复制到视图对象的对应字段
            field.set(vo,source.get(this));

        }catch (IllegalAccessException | NoSuchFieldException ignored){
            // 忽略非法访问异常和字段不存在异常
        }
    }


//    default <E, V> List<V> asViewObjectList(List<E> entityList, Class<V> viewClass, Consumer<List<V>> consumer){
//        List<V> v = this.asViewObjectList(entityList,viewClass);
//        consumer.accept(v);
//        return v;
//    }

    /**
     * 将实体对象列表转换为视图对象列表
     *
     * @param entityList 实体对象列表
     * @param viewClass  视图对象的类
     * @return 转换后的视图对象列表
     */
    default <E, V> List<V> asViewObjectList(List<E> entityList, Class<V> viewClass){
        return entityList.stream()
                .map(entity -> asViewObject(viewClass))
                .collect(Collectors.toList());
    }
}
