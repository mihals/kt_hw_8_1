# kt_hw_8_1
Не используются параметры owner_id, need_wiki, reply_to, guid, privacy, comment_privacy, privacy_comment в методах add,
createComment, deleteComment, editComment, edit.

В методах deleteComment, editComment, edit, delete вместо 1 возвращает true/false в случае успеха/неудачи.

В методах get и getComments если offset превышает максимально возможное значение, возвращается пустой список,
если offset+count больше допустимого значения возвращается список от offset до последнего элемента.
В методе get можно передать значения и в vararg.

При попытке удалить уже удаленную заметку выбрасывается исключение ItemAlreadyDeleted.
