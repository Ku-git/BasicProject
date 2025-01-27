package com.openhome.generics;

public class EitherDemo {

    public static void main(String[] args) {
        Integer a = 10;
        Integer b = 0;

        Either<String, Integer> either = div(a, b);
        if(either instanceof Left) {
            System.out.println(either.left());
        } else if(either instanceof Right) {
            System.out.println(either.right());
        }
    }

    static Either<String, Integer> div(Integer a, Integer b) {
        if(b == 0) {
            return new Left<>("除零錯誤 %d /%d".formatted(a, b));
        }
        return new Right<>(a/b);
    }

}

sealed interface Either<E, R> /*permits Left<E, R>, Right<E, R>*/ {

    default E left() {
        throw new IllegalStateException("nothing left");
    }

    default R right() {
        throw new IllegalStateException("nothing right");
    }
}

record Left<E, R>(E value) implements Either<E, R> {

    @Override
    public E left() {
        return value;
    }
}

record Right<E, R>(R value) implements Either<E, R> {

    @Override
    public R right() {
        return value;
    }
}
