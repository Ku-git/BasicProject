package com.design;

public class Facade {

    /**
     * Facade 設計模式 (也稱為外觀模式) 是一種結構型設計模式，它提供了一個簡單的接口來訪問複雜子系統中的一組接口。
     * 這樣的設計隱藏了系統內部的複雜性，並提供了一個統一的接口，使得系統更易於使用和理解
     * point:
     *  簡化接口：隱藏複雜的系統接口，提供簡單易用的接口。
     *  減少耦合：降低客戶端與子系統的耦合度，使得系統更易於維護和擴展。
     *  提升可讀性：通過簡化接口，提升系統的可讀性和可理解性。
     *  ex: 同service邏輯層的設計將dao的邏輯封裝起來
     */
    public static void main(String[] args) {
        MusicPlayerFacade musicPlayer = new MusicPlayerFacade();
        musicPlayer.playMusic("my_song.mp3");
    }
}

// 複雜子系統 - 解碼器
class Decoder {
    public void decodeAudio(String file) {
        System.out.println("Decoding audio file: " + file);
    }
}

// 複雜子系統 - 播放器
class Player {
    public void playAudio() {
        System.out.println("Playing audio");
    }
}

// 複雜子系統 - 調音器
class Tuner {
    public void setVolume(int level) {
        System.out.println("Setting volume to: " + level);
    }
}

// Facade - 音樂播放器
class MusicPlayerFacade {
    private Decoder decoder;
    private Player player;
    private Tuner tuner;

    public MusicPlayerFacade() {
        this.decoder = new Decoder();
        this.player = new Player();
        this.tuner = new Tuner();
    }

    public void playMusic(String file) {
        decoder.decodeAudio(file);
        tuner.setVolume(5);
        player.playAudio();
    }
}


