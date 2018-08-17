def es_primo (target):
  for intento in range(2,target):
    if (target % intento) == 0:
      return 0
  return 1

target = 2
count = 1

while 1:
  if es_primo(target):
    print ("El primo #", count, " es ", target)
    count += 1
  else:
    print (target, " no es primo")
  target += 1