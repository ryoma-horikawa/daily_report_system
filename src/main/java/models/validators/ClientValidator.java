package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.ClientView;
import constants.MessageConst;

/**
 * 顧客インスタンスに設定されている値のバリデーションを行うクラス
 */
public class ClientValidator {

    /**
     * 顧客インスタンスの各項目についてバリデーションを行う
     * @param cv 顧客インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(ClientView cv){
        List<String> errors = new ArrayList<String>();

        //名前のチェック
        String nameError = validateName(cv.getName());
        if (!nameError.equals("")) {
            errors.add(nameError);
        }

        //顧客情報のチェック
        String informationError = validateInformation(cv.getInformation());
        if (!informationError.equals("")) {
            errors.add(informationError);
        }

        return errors;
    }


    /**
     * 顧客の名前に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param name 会社名
     * @return エラーメッセージ
     */
    private static String validateName(String name) {
        if (name == null || name.equals("")) {
            return MessageConst.E_NONAME.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 顧客情報に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param information
     * @return エラーメッセージ
     */
    private static String validateInformation(String information) {
        if (information == null || information.equals("")) {
            return MessageConst.E_NOINFO.getMessage();
        }

      //入力値がある場合は空文字を返却
        return "";
    }
}





















