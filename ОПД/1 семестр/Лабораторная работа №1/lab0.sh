# --- ЧАСТЬ 1: Создание файлов и директорий ---
echo "--- Часть 1: Создание дерева каталогов ---"
cd ~
mkdir lab0
cd lab0
mkdir tmp

touch crustle4 marshtomp9 sceptile1
mkdir scraggy2 shuppet5 swadloon9

cat > crustle4 <<EOF
Способности Rock Blast Withdraw Sand-Attack Faint Attack
Smack Down Rock Polish Bug Bite Stealth Rock Rock Slide Slash
X-Scissor Shell Smash Flail Rock Wrecker
EOF

cat > marshtomp9 <<EOF
Тип покемона
WATER GROUND
EOF

cat > sceptile1 <<EOF
Ходы Body Slam Bullet Seed Counter
Double-Edge Dragon Pulse Drain Punch Dynamicpunch Endeavor Focus Punch
Frenzy Plant Fury Cutter Giga Drain Grass Pledge Iron Tail Low Kick
Mega Kick Mega Punch Mud-Slap Night Slash‡ Outrage Rock Climb Secret
Power Seed Bomb Seismic Toss Sleep Talk Snore Swift Synthesis
Thunderpunch Worry Seed
EOF

cd scraggy2
mkdir lucario seadra
touch snorunt
cat > snorunt <<EOF
Ходы Block Body Slam Double-Edge Icy
Wind Sleep Talk Snore Spite Water Pulse
EOF
cd ..

cd shuppet5
mkdir exeggcute mantine
touch milotic anorith ivysaur drapion
cat > milotic <<EOF
satk=10 sdef=13
spd=8
EOF
cat > anorith <<EOF
Развитые способности Swift Swim
EOF
cat > ivysaur <<EOF
Ходы Bind
Body Slam Bullet Seed Defense Curl Fury Cutter Giga Drain Grass Pledge
Knock Off Mud-Slap Natural Gift Secret Power Seed Bomb Sleep Talk
Snore String Shot Synthesis Worry Seed
EOF
cat > drapion <<EOF
weigth=135.6
height=51.0 atk=9 def=11
EOF
cd ..

cd swadloon9
mkdir happiny cherubi drifloon
touch skorupi nidoking unfezant
cat > skorupi <<EOF
weigth=26.5 height=31.0 atk=5
def=9
EOF
cat > nidoking <<EOF
weigth=136.7 height=55.0 atk=9 def=8
EOF
cat > unfezant <<EOF
Тип
диеты Carnivore
EOF
cd ..

echo "Дерево создано\n"

# --- ЧАСТЬ 2: Установка прав доступа ---
echo "--- Часть 2: Установка прав ---"

chmod u=rw,g=w,o= crustle4
chmod 664 marshtomp9
chmod u=r,g=r,o=r sceptile1
chmod u=rwx,g=wx,o=rwx scraggy2
chmod 444 scraggy2/snorunt
chmod 711 scraggy2/lucario
chmod 511 scraggy2/seadra
chmod 750 shuppet5
chmod u=,g=r,o=r shuppet5/milotic
chmod u=rw,g=w,o=w shuppet5/anorith
chmod u=wx,g=wx,o=wx shuppet5/exeggcute
chmod u=rx,g=w,o=r shuppet5/mantine
chmod u=,g=rw,o= shuppet5/ivysaur
chmod 006 shuppet5/drapion

chmod u=rx,g=wx,o=rwx swadloon9

chmod u=rx,g=x,o=wx swadloon9/happiny
chmod u=rw,g=w,o=r swadloon9/skorupi
chmod u=rwx,g=wx,o=rw swadloon9/cherubi
chmod u=,g=r,o=rw swadloon9/nidoking
chmod u=rx,g=wx,o=rwx swadloon9/drifloon
chmod u=r,g=r,o= swadloon9/unfezant

echo "Права установлены\n"

# --- ЧАСТЬ 3: Копирование элементов ---
echo "--- Часть 3: Копирование элементов ---"

cp sceptile1 swadloon9/cherubi/

chmod u+r swadloon9/nidoking
cp -r swadloon9 scraggy2/lucario/
chmod u-r swadloon9/nidoking

cat crustle4 > scraggy2/snoruntcrustle

ln -s $(pwd)/crustle4 shuppet5/anorithcrustle

cat scraggy2/snorunt shuppet5/anorith > crustle4_65

chmod u+w swadloon9
ln sceptile1 swadloon9/skorupisceptile
chmod u-w swadloon9

ln -s shuppet5 Copy_27

echo "Копирование завершено"

# --- ЧАСТЬ 4: Поиск и фильтрация ---
echo "--- Часть 4: Поиск и фильтрация ---"

grep -lR "" . 2>/dev/null | grep "t$" | xargs cat 2>/dev/null | wc -m > tmp/task4_1_result

echo "Результат 4.2:"
ls -lRtR 2>&1 | grep "ri"
echo "\n"

echo "Результат 4.3:"
grep -rnv -i "Gi" shuppet5/* 2>tmp/task4_3_errors
echo "\n"

echo "Результат 4.4:"
grep -rh "" swadloon9 2>/dev/null | sort
echo "\n"

echo "Результат 4.5:"
ls -lR 2>&1 | grep " m" | sort -k2n
echo "\n"

echo "Результат 4.6:"
wc -l scraggy2/* 2>/dev/null | sort -nr
echo "\n"

# --- ЧАСТЬ 5: Удаление ---
echo "--- Часть 5: Удаление ---"

rm -f marshtomp9

chmod u+w swadloon9
rm -f swadloon9/unfezant
chmod u-w swadloon9

rm -f shuppet5/anorithcrust*

chmod u+w swadloon9
rm -f swadloon9/skorupiscepti*

chmod -R 700 shuppet5
rm -rf shuppet5

rmdir swadloon9/drifloon

chmod u-w swadloon9

echo "Задание выполнено успешно"