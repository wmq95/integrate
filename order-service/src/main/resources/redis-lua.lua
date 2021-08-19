
if (tonumber(redis.call('get', KEYS[1])) > 0)
	then
		redis.call('incrby', KEYS[1], -1)
		return 1
	else
		return 0
end
