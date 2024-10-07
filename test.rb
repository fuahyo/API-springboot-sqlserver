text = "méxicoDescripciÃ³n: FaÃ³rmula ideal"
if text =~ /(México|méxico)/
  text = $1
end
puts text 

text = "DescripciÃ³n: FaÃ³rmula ideal"
if text =~ /(México|méxico)/
  text = $1
end

puts text 


substitutions = {
  'Ã¡' => 'á',
  'Ãº' => 'ú',
  'Ã­' => 'í',
  'Ã‘' => 'Ñ',
  'Â ' => ' ', # Assuming you want to replace Â with a single space
  'Ã©' => 'é'
}

text = "Your text here with Ã¡, Ãº, Ã­, Ã‘, Â, and Ã©"

substitutions.each do |from, to|
  text = text.gsub(from, to)
end
text = "Your text here with "

substitutions.each do |from, to|
  text = text.gsub(from, to)
end

puts text