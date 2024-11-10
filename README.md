# Simpe View Distance

## Translation
- [English](README.en.md)

Плагин позволяет игрокам менять их дальность прорисовки на сервере.
<br/><br/>

## **Возможности**
- **Нет ничего лишнего**: Плагин ориентирован на то, чтобы быть понятным любому игроку. В нем нет нагружающих функций.
- **Общие настройки**: Возможность задать стандартное значение прорисовки для всех входящих на сервер игроков.
- **Отдельно для каждого игрока**: Каждый игрок может менять свою собственную дальность прорисовки.
- **Права для отдельных групп**: Плагин позволяет легко выдавать права для изменения дальности прорисовки для каждой отдельной группы (Например для групп в LuckPerms).
- **Сохранение индивидуальных настроек**: Автоматически сохраняет и загружает кастомную дальность прорисовки игрока при выходе и входе на сервер.
- **Легко настраивать**: Конфигурация не содержат много информации и легка в настройке.

<br/>

## **Команды**
### Основная команда: `/viewdistance`
- **Использование:** `/viewdistance <число>`
- **Aliases:** `vd`
- Устанавливает дальность прорисовки на новое значение
### Подкоманды
#### - `reload`
- **Использование:** `/vd reload`
- Перезагружает конфигурацию плагина

<br/>

## **Права**
- Использовать команду /viewdistance: `viewdistance.set`
- Перезагружать конфигурацию плагина: `viewdistance.reload`

- `viewdistance.reload` по умолчанию имеют только операторы
- `viewdistance.set` по умолчанию имеют все игроки

## config.yml
```yaml
# Минимально возможная дальность прорисовки (Не может быть меньше чем 2)
min-distance: 2

# Дальность прорисовки по группам, выдается правом 'group.<name>' (выдается автоматически всем группам в LuckPerms)
# Игроки не смогут установить значение больше чем то, которое указано здесь
# Если игрок состоит в нескольких группах, то будет выбрано наибольшее значение
distances:
  default: 12 # Стандартное значение для всех игроков (Не может быть больше чем 32)
  moder: 16
  admin: 32

#----------- Сообщения -----------
# Для цвета используется символ '§'

# Префикс перед сообщениями, если хочешь убрать то просто сделай его пустым - ''
prefix: '§f[§lV§r§7iew§f§lD§r§7instance§f] §r'

# Сообщение показывается при изменении дальности прорисовки. '{chunks}' - PlaceHolder для значения дальности прорисовки
view-distance-change-msg: '§aВы изменили вашу дальность прорисовки на {chunks} чанков'

# Сообщение показывается при ошибке в синтаксисе команды
incorrect-args-msg: '§cОшибка синтаксиса§e - используйте §l/vd <число>'

# Сообщение показывается при перезагрузке конфига (/vd reload)
reload-config-msg: '§aКонфиг успешно перезагружен'

# Сообщение показывается при отсутствии прав для выполнения команды
no-permission-msg: '§cУ вас нет прав для использования данной команды'
```
