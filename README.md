# IAHook

ItemsAdder hook for EmpireSMP

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
```