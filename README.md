[![CodeFactor](https://www.codefactor.io/repository/github/errror404/durabilityplugin/badge)](https://www.codefactor.io/repository/github/errror404/durabilityplugin)
# DurabilityPlugin
마인크래프트의 모든 내구도를 가진 아이템들의 내구도가 제작 시 랜덤으로 생성되는 미니게임 플러그인
## Commands
- d_start : 미니게임을 시작합니다.
- d_stop : 미니게임을 종료합니다.
## Rules
게임 시작 시 플레이어의 게임모드가 모두 서바이벌로 업데이트되며, 랜덤 좌표로 스폰됩니다.

난이도는 어려움으로 고정되며, 시작 시 소유하고 있던 모든 아이템들은 사라지게 됩니다.
플레이어의 스폰 범위는 난수의 좌표로부터 7500 * 7500입니다.
월드보더가 3시간 가량에 걸쳐 지속해서 줄어들며, 월드보다 바깥에 있는 경우 지속해서 데미지를 입게됩니다. (오버월드에서만 해당)
플레이어가 사망할 경우 채팅창에 해당 플레이어의 등수가 출력되며, 게임모드가 관전모드로 업데이트됩니다.
관전자들이 친 채팅은 관전자들만 볼 수 있으며, 플레이어가 죽었을때 남은 플레이어가 1명 이하인 경우 게임이 자동으로 종료됩니다.
