package com.openhome.generics;

public class SealDemo {
}

abstract sealed class Role permits Knight, Warrior, Mage, Archer {

}

sealed class Knight extends Role permits DragonKnight, MarinKnight {}
non-sealed class Warrior extends Role {}
non-sealed class Mage extends Role {}
final class Archer extends Role {}

final class DragonKnight extends Knight {}
final class MarinKnight extends Knight {}