package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.ClientView;
import constants.MessageConst;
import services.ClientService;

/**
 * 顧客インスタンスに設定されている値のバリデーションを行うクラス
 */
public class ClientValidator {

    /**
     * 顧客インスタンスの各項目についてバリデーションを行う
     * @param cv 顧客インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(
            ClientService service, ClientView cv,Boolean cli_codeDuplicateCheckFlag){
        List<String> errors = new ArrayList<String>();

        //顧客番号のチェック
        String cli_codeError = validateCli_code(service, cv.getCli_code(), cli_codeDuplicateCheckFlag);
        if (!cli_codeError.equals("")) {
            errors.add(cli_codeError);
        }

        //会社名のチェック
        String cli_nameError = validateCli_name(cv.getCli_name());
        if (!cli_nameError.equals("")) {
            errors.add(cli_nameError);
        }

        //顧客情報のチェック
        String informationError = validateInformation(cv.getInformation());
        if (!informationError.equals("")) {
            errors.add(informationError);
        }

        return errors;
    }

    /**
     * 顧客番号の入力チェックを行い、エラーメッセージを返却
     * cli_code 顧客番号
     * codeDuplicateCheckFlag 顧客番号の重複チェックを実施するかどうか(実施する:true 実施しない:false)
     */
    private static String validateCli_code(ClientService service, String cli_code, Boolean cli_codeDuplicateCheckFlag) {

        //入力値がなければエラーメッセージを返却
        if (cli_code == null || cli_code.equals("")) {
            return MessageConst.E_NOCLI_CLICODE.getMessage();
        }

        if (cli_codeDuplicateCheckFlag) {
            //顧客番号の重複チェックを実施

            long clientConst = isDuplicateClient(service, cli_code);

            //同一の顧客番号が既に登録されている場合はエラーメッセージを返却
            if (clientConst > 0) {
                return MessageConst.E_CLI_CLICODE_EXIST.getMessage();
            }
        }

        return "";
    }

    private static long isDuplicateClient(ClientService service, String cli_code) {

        long clientsCount = service.countByCli_code(cli_code);
        return clientsCount;
    }


    /**
     * 顧客の名前に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param name 会社名
     * @return エラーメッセージ
     */
    private static String validateCli_name(String cli_name) {
        if (cli_name == null || cli_name.equals("")) {
            return MessageConst.E_NOCLI_CLINAME.getMessage();
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





















