package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Client;

/**
 * 顧客データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class ClientConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     *@param rv ClientViewのインスタンス
     *@return Clientのインスタンス
     */
    public static Client toModel(ClientView cv) {
        return new Client(
                cv.getId(),
                EmployeeConverter.toModel(cv.getEmployee()),
                cv.getName(),
                cv.getInformation(),
                cv.getAddress(),
                cv.getPhone(),
                cv.getEmail(),
                cv.getCustomer(),
                cv.getManager(),
                cv.getCreatedAt(),
                cv.getUpdatedAt()
                );
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param r Clientのインスタンス
     * @return ClientViewのインスタンス
     */
    public static ClientView toView(Client c) {

        if(c == null) {
            return null;
        }

        return new ClientView(
                c.getId(),
                EmployeeConverter.toView(c.getEmployee()),
                c.getName(),
                c.getInformation(),
                c.getAddress(),
                c.getPhone(),
                c.getEmail(),
                c.getCustomer(),
                c.getManager(),
                c.getCreatedAt(),
                c.getUpdatedAt()
                );
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<ClientView> toViewList(List<Client> list){
        List<ClientView> evs = new ArrayList<>();

        for (Client c : list) {
            evs.add(toView(c));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param r DTOモデル(コピー先)
     * @param rv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Client c, ClientView cv) {
        c.setId(cv.getId());
        c.setEmployee(EmployeeConverter.toModel(cv.getEmployee()));
        c.setName(cv.getName());
        c.setInformation(cv.getInformation());
        c.setAddress(cv.getAddress());
        c.setPhone(cv.getPhone());
        c.setEmail(cv.getEmail());
        c.setCustomer(cv.getCustomer());
        c.setManager(cv.getManager());
        c.setCreatedAt(cv.getCreatedAt());
        c.setUpdatedAt(cv.getUpdatedAt());
    }

}















