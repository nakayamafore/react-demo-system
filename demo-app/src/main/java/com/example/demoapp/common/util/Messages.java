package com.example.demoapp.common.util;

import java.util.Locale;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
public class Messages {

  private static MessageSourceAccessor messageSourceAccessor;

  public Messages(MessageSourceAccessor messageSourceAccessor) {
    Messages.messageSourceAccessor = messageSourceAccessor;
  }

  @RequiredArgsConstructor
  public static class Message {

    private final String key;

    /**
     * メッセージ取得
     *
     * @return 整形後メッセージ
     */
    public String getMessage() {
      return messageSourceAccessor.getMessage(key, Locale.getDefault());
    }

    /**
     * 引数付きメッセージ取得
     *
     * @param messageArgs メッセージ引数
     * @return 整形後メッセージ
     */
    public String message(Object... messageArgs) {
      return messageSourceAccessor.getMessage(key, messageArgs, Locale.getDefault());
    }
  }

  /** {0}が重複しています */
  public static final Message IS_CONFLICT =
      new Message("demoapp.isConflict");
  /** {0}が存在しません */
  public static final Message IS_NOT_EXIST =
      new Message("demoapp.isNotExist");

  /**
   * メッセージ引数クラス
   */
  public static class Items {
    /** 企業コード */
    public static final DefaultMessageSourceResolvable COMPANY_CODE =
        new DefaultMessageSourceResolvable("demoapp.item.companycode");
  }
}
