package com.design;

public class AbstractFactory {

    /**
     * 抽象工廠模式（Abstract Factory Pattern）：
     * 個人理解: 藉由抽象的工廠(依賴於抽象)衍生出可以產生不同組合的工廠，再藉由單一的工廠接口(關注分離)，設計可以更加彈性
     * 用於創建一系列相關或互相依賴的對象。它提供了一個接口，用於創建一族相關或依賴的對象，且不需要指定具體類。
     * 典型的抽象工廠模式包括：
     * 1. 抽象工廠接口，定義多個創建方法。
     * 2. 具體工廠實現，實現這些創建方法。
     * 3. 抽象產品接口和具體產品實現。
     * point:
     * 1.依賴倒置原則（Dependency Inversion Principle）：
     *  這段程式碼中，FactoryTest 類依賴於抽象的 IFactory 接口，而不是具體的工廠實現。這使得程式碼更加靈活和可擴展。
     * 2.開放封閉原則（Open/Closed Principle）：
     *  可以通過新增具體工廠來擴展系統，而無需修改現有代碼。比如，可以新增 I2C_Factory 來創建 I2C 通訊產品。
     * 3.單一責任原則（Single Responsibility Principle）：
     *  每個具體工廠和產品實現都有各自明確的責任，遵循單一責任原則。
     */
    public static void main(String[] args) {

        // 由工廠來產生不同目標
        AbstractFactory factoryTest = new AbstractFactory();

        // 使用工廠提供者來獲取具體工廠
        IFactory spiFactory = FactoryProvider.getFactory("SPI");
        IFactory uartFactory = FactoryProvider.getFactory("UART");
        IFactory mixFactory = FactoryProvider.getFactory("MIX");

        factoryTest.testFactory(spiFactory);
        factoryTest.testFactory(uartFactory);
        factoryTest.testFactory(mixFactory);
    }

    void testFactory(IFactory factory) {
        System.out.println("Factory implement: " + factory.getClass().getSimpleName());

        ICreate iCreate = factory.create();
        iCreate.create();

        IStart startCode = factory.getStartCode();
        System.out.println("Comm start code: " + startCode.getStartCode());

        System.out.println("// -------------------------------\n");
    }
}

class FactoryProvider {
    public static IFactory getFactory(String type) {
        switch (type.toUpperCase()) {
            case "SPI":
                return new SPI_Factory();
            case "UART":
                return new UART_Factory();
            case "MIX":
                return new MIX_Factory();
            default:
                throw new IllegalArgumentException("Unknown factory type");
        }
    }
}

//abstract product
interface ICreate {
    void create();
}

//abstract product
interface IStart {
    String getStartCode();
}

//concrete product
class CreateSPI implements ICreate {
    @Override
    public void create() {
        System.out.println("Start create Spi comm");
    }
}
//concrete product
class CreateUART implements ICreate {
    @Override
    public void create() {
        System.out.println("Start create UART comm");
    }
}
//concrete product
class StartSPI implements IStart {
    @Override
    public String getStartCode() {
        return "SSS_PPP_III";
    }
}
//concrete product
class StartUART implements IStart {
    @Override
    public String getStartCode() {
        return "UUU_AAA_RRR_TTT";
    }
}

//abstract factory
interface IFactory {
    ICreate create();
    IStart getStartCode();
}

// concrete factory
class SPI_Factory implements IFactory {
    @Override
    public ICreate create() {
        return new CreateSPI();
    }
    @Override
    public IStart getStartCode() {
        return new StartSPI();
    }
}

// concrete factory
class UART_Factory implements IFactory {
    @Override
    public ICreate create() {
        return new CreateUART();
    }
    @Override
    public IStart getStartCode() {
        return new StartUART();
    }
}

// concrete factory
class MIX_Factory implements IFactory {
    @Override
    public ICreate create() {
        return new CreateSPI();
    }
    @Override
    public IStart getStartCode() {
        return new StartUART();
    }
}
