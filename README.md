# IAHook

ItemsAdder hook for EmpireSMP
## Commands
- `iahookdisable` - disable
- `iahookreload` - reload

## Hooks

#### [#5 damage_near_entities disable armor bypass](https://github.com/Astra-Interactive/IaHook/issues/5)

Для того, чтобы убрать байпасс брони плагином - необходимо добавить список ID предметов, которые должны проверяться

```yaml
# config.yml
damageEntitiesItemIds:
  - "master_fighting_wand"
```

Логгирование

```yaml
logging:
  logDamageEntities: true
  manaRestoration: true
  moneyPickUp: true
```

ManaConfiguration

```yaml
mana_configuration:
  id: "NAMESPACE:mana"
  min: 0.0
  max: 20.0
  passive_mana_restore:
    "saphire_chestplate":
      id: "saphire_chestplate"
      every_millis: 300
      mana: 1.0
```

Money pick yp

При поднятии этого предмета - он будет удален, а взамен игроку будет добавлен баланс в соответствующем размере

```yaml
money_pickup:
  sound: minecraft:block.beehive.drip
  items:
    DIAMOND:
      item: DIAMOND
      min: 5.0
      max: 10.0
```

Thirst integration

```yaml
thirst:
  water_bottle:
    min: 0
    max: 1
  in_water:
    min: 0
    max: 1
  in_rain:
    min: 0
    max: 1

```