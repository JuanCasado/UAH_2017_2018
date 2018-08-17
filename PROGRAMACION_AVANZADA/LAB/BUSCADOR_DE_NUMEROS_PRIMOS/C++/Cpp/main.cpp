#include <stdio.h>

bool es_primo (int target)
{
	for (int intento = 2; intento < target; intento++)
		if (!(target % intento))
			return 0;
	return 1;
}

int main()
{
	int target = 2;
	int count = 1;
	while (1)
	{
		if (es_primo(target))
		{
			printf("El primo %d es %d\n", count, target);
			count++;
		}
		else
			printf("%d no es primo\n", target);
		target++;
	}
	return 0;
}